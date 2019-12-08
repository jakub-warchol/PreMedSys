package pl.pwsztar.premedsys.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class InMemoryDiseasesSymptomesRepository implements DiseasesSymptomesRepository{

  Map<Long, DiseasesSymptomes> entity = new ConcurrentHashMap<>();

  @Override
  public List<DiseasesSymptomes> findAll() {
    return new ArrayList<>(entity.values());
  }

  @Override
  public DiseasesSymptomes save(DiseasesSymptomes s) {
    return entity.put(new Random().nextLong(), s);
  }

  @Override
  public List<DiseasesSymptomes> findBySymptomeIn(List<String> symptomes) {
    return entity.values()
      .stream()
      .filter(el -> symptomes.contains(el.getSymptome()))
      .collect(Collectors.toList());
  }

  @Override
  public Long countByDiseaseId(Long diseaseId) {
    return entity.values().stream()
      .filter(el -> el.getDiseaseId().equals(diseaseId))
      .count();
  }

  /*
  * Unused methods */

  @Override
  public List<DiseasesSymptomes> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<DiseasesSymptomes> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public List<DiseasesSymptomes> findAllById(Iterable<Long> iterable) {
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
  public void delete(DiseasesSymptomes diseasesSymptomes) {

  }

  @Override
  public void deleteAll(Iterable<? extends DiseasesSymptomes> iterable) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public <S extends DiseasesSymptomes> List<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public Optional<DiseasesSymptomes> findById(Long aLong) {
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
  public <S extends DiseasesSymptomes> S saveAndFlush(S s) {
    return null;
  }

  @Override
  public void deleteInBatch(Iterable<DiseasesSymptomes> iterable) {

  }

  @Override
  public void deleteAllInBatch() {

  }

  @Override
  public DiseasesSymptomes getOne(Long aLong) {
    return null;
  }

  @Override
  public <S extends DiseasesSymptomes> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends DiseasesSymptomes> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends DiseasesSymptomes> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends DiseasesSymptomes> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends DiseasesSymptomes> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends DiseasesSymptomes> boolean exists(Example<S> example) {
    return false;
  }
}
