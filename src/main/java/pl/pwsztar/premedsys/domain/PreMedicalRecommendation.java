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
@Table(name = "recommendation")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
class PreMedicalRecommendation {

  @Id
  @Column(name = "recommendation_id")
  Long recommendationId;

  @Column(name = "disease_id")
  Long diseaseId;

  @Column(name = "recommendation_content")
  String recommendation;
}
