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
@Table(name = "gallerist_car",
        uniqueConstraints =
                {@UniqueConstraint(columnNames =
                        {"gallerist_id","car_id"},
                        name = "uq_gallerist_car")})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GalleristCar extends BaseEntity{

    @ManyToOne
    private Gallerist gallerist;

    @ManyToOne
    private Car car;

}
