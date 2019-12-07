package pl.pwsztar.premedsys.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.pwsztar.premedsys.dto.SymptomesDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diseases_symptomes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
class DiseasesSymptomes {

  @Id
  @Column(name = "disease_symptomes_id")
  Long diseaseSymptomesId;

  @Column(name = "disease_id")
  @Getter
  Long diseaseId;

  @Column(name = "symptomes_content")
  @Getter
  String symptome;

  public SymptomesDto dto() {
    return SymptomesDto.builder()
      .symptomeName(symptome)
      .build();
  }
}
