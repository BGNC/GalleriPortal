package com.bgnc.galleriportal.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IGenericService<TEntity, TKey> {

    TEntity save(TEntity entity);

    List<TEntity> saveAll(List<TEntity> entities);

    Optional<TEntity> findById(TKey id);

    List<TEntity> findAll();

    Page<TEntity> findAll(Pageable pageable);

    void deleteById(TKey id);

    void delete(TEntity entity);

    void deleteAll(List<TEntity> entities);

    void deleteAll();

    long count();

    boolean existsById(TKey id);
}
