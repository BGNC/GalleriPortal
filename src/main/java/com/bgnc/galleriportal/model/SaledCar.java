package com.bgnc.galleriportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "saled_car",uniqueConstraints = {@UniqueConstraint(columnNames = {"gallerist_id","car_id","customer_id"},name = "uq_constraint_saled_car")})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaledCar extends BaseEntity {

    @ManyToOne
    private Gallerist gallerist;
    @ManyToOne
    private Car car;
    @ManyToOne
    private Customer customer;
}
