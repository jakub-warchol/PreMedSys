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
public class DiseaseFacadeCreator {

  DiseasesSymptomesRepository symptomesRepository;
  DiseasesRepository diseasesRepository;
  PreMedicalRecommendationRepository recommendationRepository;

  @Autowired
  public DiseaseFacadeCreator(@Qualifier("diseasesSymptomesRepository") DiseasesSymptomesRepository symptomesRepository,
                              DiseasesRepository diseasesRepository,
                              @Qualifier("preMedicalRecommendationRepository") PreMedicalRecommendationRepository recommendationRepository) {
    this.symptomesRepository = symptomesRepository;
    this.diseasesRepository = diseasesRepository;
    this.recommendationRepository = recommendationRepository;
  }

 public  DiseaseFacade create(DiseasesSymptomesRepository symptomesRepository, DiseasesRepository diseasesRepository,
                       PreMedicalRecommendationRepository recommendationRepository) {
    return DiseaseFacade.builder()
      .diseasesRepository(diseasesRepository)
      .symptomesRepository(symptomesRepository)
      .recommendationRepository(recommendationRepository)
      .build();
  }

  @Bean
   public DiseaseFacade create() {
    return create(symptomesRepository, diseasesRepository, recommendationRepository);
  }
}
