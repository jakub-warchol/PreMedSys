package pl.pwsztar.premedsys.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface DiseasesSymptomesRepository  extends JpaRepository<DiseasesSymptomes, Long> {
  List<DiseasesSymptomes> findBySymptomeIn(List<String> symptomes);

  Long countByDiseaseId(Long diseaseId);
}
