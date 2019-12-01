package pl.pwsztar.premedsys.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface DiseasesRepository extends JpaRepository<Diseases, Long> {
}
