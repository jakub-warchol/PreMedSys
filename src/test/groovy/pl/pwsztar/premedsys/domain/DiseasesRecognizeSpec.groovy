package pl.pwsztar.premedsys.domain

import io.vavr.collection.List
import org.junit.Before
import spock.lang.Specification

class DiseasesRecognizeSpec  extends Specification {
  DiseasesSymptomesRepository symptomesRepository = new InMemoryDiseasesSymptomesRepository()
  DiseasesRepository diseasesRepository = new InMemoryDiseasesRepository()
  PreMedicalRecommendationRepository recommendationRepository = new InMemoryPreMedicalRecommendationRepository()
  DiseaseFacade diseaseFacade = new DiseaseFacadeCreator().create(symptomesRepository, diseasesRepository, recommendationRepository)
  def symptomesIds = [11L, 22L, 33L]
  def diseasesIds = [44L, 55L, 66L]
  def recomendationsIds = [77L, 88L, 99L]

  @Before
  def prepare() {
    for( i in 0..2) {
      symptomesRepository.save(new DiseasesSymptomes(symptomesIds.get(i), diseasesIds.get(i), "Symptome" + i))
      diseasesRepository.save(new Diseases(diseasesIds.get(i), "Choroba" + i, "Morbus" + i))
      recommendationRepository.save(new PreMedicalRecommendation(recomendationsIds.get(i), diseasesIds.get(i), "Recommendation" + i))
    }
    symptomesRepository.save(new DiseasesSymptomes(symptomesIds.get(2), diseasesIds.get(1), "Symptome" + 3))
    symptomesRepository.save(new DiseasesSymptomes(symptomesIds.get(2), diseasesIds.get(2), "Symptome" + 2))
  }


  def "user should be able to get list of all symptomes"() {
    when: "user asks for symptomes"
      def results = diseaseFacade.getDiseasesSymptomes()
    then: "user gets list of them"
      results.map({el -> el.symptomeName}).sorted().toJavaList() ==
        ["Symptome0", "Symptome1", "Symptome2", "Symptome3"]
  }

  def "user should be able to get list of all all recognizable diseases"() {
    when: "user asks for diseases"
      def results = diseaseFacade.getDiseases()
    then: "user gets list of them latin name"
      results.map({el -> el.diseaseLatinName}).sorted().toJavaList() ==
        ["Morbus0", "Morbus1", "Morbus2"]
    and: "user gets list of them polish name"
      results.map({el -> el.diseaseName}).sorted().toJavaList() ==
        ["Choroba0", "Choroba1", "Choroba2"]
  }

  def "user should be able to get list of diseases based on his pointed symptomes"() {
    when: "user selects symptomes"
      def results = diseaseFacade.getDiseasesBySymptomesName(List.of("Symptome2", "Symptome3"))
    then: "user gets list of diseases"
      results.map({el -> el.diseaseName}).sorted().toJavaList() == ["Choroba1", "Choroba2"]
  }

  def "user should be able to get probable recognition based on symptomes"() {
    when: "user selects symptomes"
      def results = diseaseFacade.getDiseasesAndRecommendationsBySymptomesName(["Symptome2", "Symptome3", "Symptome0"])
    then: "user gets recommendations"
      results.map({el -> el.recommendations}).sorted().toJavaList() ==
        ["Recommendation0", "Recommendation1", "Recommendation2"]
    and: "user gets disease name"
      results.map({el -> el.diseaseName}).sorted().toJavaList() ==
        ["Choroba0", "Choroba1", "Choroba2"]
  }
}
