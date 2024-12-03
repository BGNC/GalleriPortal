package com.bgnc.galleriportal.repository;

import com.bgnc.galleriportal.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository  extends GenericRepository<Car,Long> {
}
