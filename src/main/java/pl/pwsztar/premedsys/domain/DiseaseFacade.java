package pl.pwsztar.premedsys.domain;

import io.vavr.collection.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import pl.pwsztar.premedsys.dto.DiseasesDto;
import pl.pwsztar.premedsys.dto.PreMedicalResultsDto;
import pl.pwsztar.premedsys.dto.SymptomesDto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;


@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiseaseFacade {

  DiseasesRepository diseasesRepository;
  DiseasesSymptomesRepository symptomesRepository;
  PreMedicalRecommendationRepository recommendationRepository;

  public List<PreMedicalResultsDto> getDiseasesAndRecommendationsBySymptomesName(List<String> symptomes) {
    return null;
  }
  public List<DiseasesDto> getDiseases() {
    return List.ofAll(diseasesRepository.findAll())
      .map(Diseases::dto);
  }

  public List<SymptomesDto> getDiseasesSymptomes() {
    return List.ofAll(symptomesRepository.findAll())
      .filter(distinctByKey(DiseasesSymptomes::getSymptome))
      .map(DiseasesSymptomes::dto);
  }

   private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

  public List<DiseasesDto> getDiseasesBySymptomesName(List<String> symptomes) {
    List<Long> symptomeIds = List.ofAll(symptomesRepository.findBySymptomeIn(symptomes.toJavaList()))
      .map(DiseasesSymptomes::getDiseaseId);

    List<DiseasesDto> diseasesList = List.ofAll(diseasesRepository.findAllById(symptomeIds.toJavaList()))
      .map(Diseases::dto);

    if(diseasesList.isEmpty()) {
      //TODO: exception/either
      return List.empty();
    }
    return diseasesList;
  }
}
