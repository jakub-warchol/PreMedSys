package pl.pwsztar.premedsys.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryDiseasesRepository  implements DiseasesRepository {

  Map<Long, Diseases> entity = new ConcurrentHashMap<>();

  @Override
  public List<Diseases> findAll() {
    return new ArrayList<>(entity.values());
  }

  @Override
  public List<Diseases> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<Diseases> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public List<Diseases> findAllById(Iterable<Long> iterable) {
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
  public void delete(Diseases diseases) {

  }

  @Override
  public void deleteAll(Iterable<? extends Diseases> iterable) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public Diseases save(Diseases s) {
    return entity.put(new Random().nextLong(), s);
  }

  @Override
  public <S extends Diseases> List<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public Optional<Diseases> findById(Long aLong) {
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
  public <S extends Diseases> S saveAndFlush(S s) {
    return null;
  }

  @Override
  public void deleteInBatch(Iterable<Diseases> iterable) {

  }

  @Override
  public void deleteAllInBatch() {

  }

  @Override
  public Diseases getOne(Long aLong) {
    return null;
  }

  @Override
  public <S extends Diseases> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends Diseases> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends Diseases> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends Diseases> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends Diseases> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends Diseases> boolean exists(Example<S> example) {
    return false;
  }
}
