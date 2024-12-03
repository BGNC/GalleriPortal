package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.model.BaseEntity;
import com.bgnc.galleriportal.repository.GenericRepository;
import com.bgnc.galleriportal.service.IGenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenericServiceImpl<TEntity extends BaseEntity, TKey> implements IGenericService<TEntity, TKey> {

    private final GenericRepository<TEntity, TKey> genericRepository;

    @Override
    public TEntity save(TEntity entity) {
        return genericRepository.save(entity);
    }

    @Override
    public List<TEntity> saveAll(List<TEntity> entities) {
        return genericRepository.saveAll(entities);
    }

    @Override
    public Optional<TEntity> findById(TKey id) {
        return genericRepository.findById(id);
    }

    @Override
    public List<TEntity> findAll() {
        return genericRepository.findAll();
    }

    @Override
    public Page<TEntity> findAll(Pageable pageable) {
        return genericRepository.findAll(pageable);
    }

    @Override
    public void deleteById(TKey id) {
        genericRepository.deleteById(id);
    }

    @Override
    public void delete(TEntity entity) {
        genericRepository.delete(entity);
    }

    @Override
    public void deleteAll(List<TEntity> entities) {
        genericRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        genericRepository.deleteAll();
    }

    @Override
    public long count() {
        return genericRepository.count();
    }

    @Override
    public boolean existsById(TKey id) {
        return genericRepository.existsById(id);
    }
}
