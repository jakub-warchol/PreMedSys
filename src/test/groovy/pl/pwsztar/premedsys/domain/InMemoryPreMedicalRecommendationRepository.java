package pl.pwsztar.premedsys.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class InMemoryPreMedicalRecommendationRepository implements PreMedicalRecommendationRepository{

  Map<Long, PreMedicalRecommendation> entity = new ConcurrentHashMap<>();

  @Override
  public List<PreMedicalRecommendation> findAll() {
    return new ArrayList<>(entity.values());
  }

  @Override
  public PreMedicalRecommendation save(PreMedicalRecommendation s) {
    return entity.put(new Random().nextLong(), s);
  }

  @Override
  public List<PreMedicalRecommendation> findAllByDiseaseIdIn(List<Long> ids) {
    List<PreMedicalRecommendation> collect = entity.values().stream()
      .filter(el -> ids.contains(el.getDiseaseId()))
      .collect(Collectors.toList());
    return collect;
  }

  /*
  * Unused methods */

  @Override
  public List<PreMedicalRecommendation> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<PreMedicalRecommendation> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public List<PreMedicalRecommendation> findAllById(Iterable<Long> iterable) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(Long aLong) {

  }

  @Override
  public void delete(PreMedicalRecommendation preMedicalRecommendation) {

  }

  @Override
  public void deleteAll(Iterable<? extends PreMedicalRecommendation> iterable) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public <S extends PreMedicalRecommendation> List<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public Optional<PreMedicalRecommendation> findById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(Long aLong) {
    return false;
  }

  @Override
  public void flush() {

  }

  @Override
  public <S extends PreMedicalRecommendation> S saveAndFlush(S s) {
    return null;
  }

  @Override
  public void deleteInBatch(Iterable<PreMedicalRecommendation> iterable) {

  }

  @Override
  public void deleteAllInBatch() {

  }

  @Override
  public PreMedicalRecommendation getOne(Long aLong) {
    return null;
  }

  @Override
  public <S extends PreMedicalRecommendation> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends PreMedicalRecommendation> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends PreMedicalRecommendation> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends PreMedicalRecommendation> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends PreMedicalRecommendation> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends PreMedicalRecommendation> boolean exists(Example<S> example) {
    return false;
  }
}
