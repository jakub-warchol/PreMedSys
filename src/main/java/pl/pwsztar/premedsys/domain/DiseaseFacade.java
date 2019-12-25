package pl.pwsztar.premedsys.domain;

import io.vavr.collection.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import pl.pwsztar.premedsys.dto.DiseasesDto;
import pl.pwsztar.premedsys.dto.PreMedicalResultsDto;
import pl.pwsztar.premedsys.dto.SymptomesDto;
import pl.pwsztar.premedsys.exception.UnrecognizedSymptomesException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiseaseFacade {

  DiseasesRepository diseasesRepository;
  DiseasesSymptomesRepository symptomesRepository;
  PreMedicalRecommendationRepository recommendationRepository;

  public List<DiseasesDto> getDiseases() {
    return List.ofAll(diseasesRepository.findAll())
      .map(Diseases::dto);
  }

  public List<SymptomesDto> getDiseasesSymptomes() {
    return List.ofAll(symptomesRepository.findAll())
      .filter(distinctByKey(DiseasesSymptomes::getSymptome))
      .map(DiseasesSymptomes::dto);
  }

  public List<PreMedicalResultsDto> getDiseasesAndRecommendationsBySymptomesName(java.util.List<String> symptomes) throws UnrecognizedSymptomesException {
    List<Long> symptomeIds = List.ofAll(symptomesRepository.findBySymptomeIn(symptomes))
      .map(DiseasesSymptomes::getDiseaseId);

    List<Diseases> diseasesList = List.ofAll(diseasesRepository.findAllById(symptomeIds.toJavaList()));
    if (diseasesList.isEmpty()) {
      throw new UnrecognizedSymptomesException("Symptomes " + symptomes.toString() + " cannot be recognized by server!");
    }

    List<PreMedicalRecommendation> recommendations = List.ofAll(recommendationRepository.findAllByDiseaseIdIn(diseasesList
      .map(Diseases::getDiseaseId).toJavaList()));

    Map<Long, Long> recognizedSymptomesCount = getSymptomesCountBySymptomeIds(symptomeIds);
    Map<Long, Double> allDiseaseSymptomesCount = getAllSymptomesCountBySymptomeIds(symptomeIds);

    return  getRecognitionResults(diseasesList, recommendations, recognizedSymptomesCount, allDiseaseSymptomesCount)
      .filter(distinctByKey(PreMedicalResultsDto::getDiseaseName))
      .sortBy(PreMedicalResultsDto::getDiseaseProbability);
  }

  private List<PreMedicalResultsDto> getRecognitionResults(List<Diseases> diseasesList,
                                                           List<PreMedicalRecommendation> recommendations,
                                                           Map<Long, Long> recognizedSymptomesCount,
                                                           Map<Long, Double> allDiseaseSymptomesCount) {

    return recommendations.map(el -> PreMedicalResultsDto.builder()
      .diseaseName(diseasesList
        .filter(diseases -> diseases.getDiseaseId().equals(el.getDiseaseId())).get(0).getDiseaseName())
      .diseaseProbability(recognizedSymptomesCount.get(el.getDiseaseId()) / allDiseaseSymptomesCount.get(el.getDiseaseId()))
      .recommendations(el.getRecommendation())
      .build());
  }

  private Map<Long, Double> getAllSymptomesCountBySymptomeIds(List<Long> symptomeIds) {
    Map<Long, Double> diseasesSymptomesMap = new ConcurrentHashMap<>();
    symptomeIds.forEach(id -> diseasesSymptomesMap.put(id, (double) symptomesRepository.countByDiseaseId(id) / 1.0));
    return diseasesSymptomesMap;
  }

  private Map<Long, Long> getSymptomesCountBySymptomeIds(List<Long> symptomeIds) {
    return symptomeIds.toJavaList().stream()
      .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
  }

  private List<DiseasesDto> getDiseasesBySymptomesName(List<String> symptomes) throws UnrecognizedSymptomesException {
    List<Long> symptomeIds = List.ofAll(symptomesRepository.findBySymptomeIn(symptomes.toJavaList()))
      .map(DiseasesSymptomes::getDiseaseId);

    List<DiseasesDto> diseasesList = List.ofAll(diseasesRepository.findAllById(symptomeIds.toJavaList()))
      .map(Diseases::dto);

    if(diseasesList.isEmpty()) {
      throw new UnrecognizedSymptomesException("Symptomes " + symptomes.toString() + " cannot be recognized by server!");
    }
    return diseasesList;
  }

  private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }
}
