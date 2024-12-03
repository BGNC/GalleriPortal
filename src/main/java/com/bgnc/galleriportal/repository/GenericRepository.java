package com.bgnc.galleriportal.repository;

import com.bgnc.galleriportal.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericRepository <TEntity extends BaseEntity,TKey> extends JpaRepository<TEntity, TKey> {


   


}
