package pl.pwsztar.premedsys.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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
  Long diseaseId;

  @Column(name = "disease_name")
  String diseaseName;

  @Column(name = "disease_name_latin")
  String diseaseLatinName;
}
