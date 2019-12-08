package pl.pwsztar.premedsys.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class DiseaseFacadeCreator {

  DiseasesSymptomesRepository symptomesRepository;
  DiseasesRepository diseasesRepository;
  PreMedicalRecommendationRepository recommendationRepository;

  @Autowired
  DiseaseFacadeCreator(@Qualifier("diseasesSymptomesRepository") DiseasesSymptomesRepository symptomesRepository,
                              DiseasesRepository diseasesRepository,
                              @Qualifier("preMedicalRecommendationRepository") PreMedicalRecommendationRepository recommendationRepository) {
    this.symptomesRepository = symptomesRepository;
    this.diseasesRepository = diseasesRepository;
    this.recommendationRepository = recommendationRepository;
  }

  DiseaseFacade create(DiseasesSymptomesRepository symptomesRepository, DiseasesRepository diseasesRepository,
                       PreMedicalRecommendationRepository recommendationRepository) {
    return DiseaseFacade.builder()
      .diseasesRepository(diseasesRepository)
      .symptomesRepository(symptomesRepository)
      .recommendationRepository(recommendationRepository)
      .build();
  }

  @Bean
   DiseaseFacade create() {
    return create(symptomesRepository, diseasesRepository, recommendationRepository);
  }
}
