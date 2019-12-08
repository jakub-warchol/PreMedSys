package pl.pwsztar.premedsys.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PreMedicalRecommendationRepository extends JpaRepository<PreMedicalRecommendation, Long> {
  List<PreMedicalRecommendation> findAllByDiseaseIdIn(List<Long> ids);
}
