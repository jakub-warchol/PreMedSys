package pl.pwsztar.premedsys.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.pwsztar.premedsys.dto.DiseasesDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "diseases")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
class Diseases {

  @Id
  @Column(name = "disease_id")
  @Getter
  Long diseaseId;

  @Column(name = "disease_name")
  @Getter
  String diseaseName;

  @Column(name = "disease_name_latin")
  String diseaseLatinName;

  DiseasesDto dto() {
    return DiseasesDto.builder()
      .diseaseName(diseaseName)
      .diseaseLatinName(diseaseLatinName)
      .build();
  }
}
