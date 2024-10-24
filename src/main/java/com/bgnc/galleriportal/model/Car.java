package com.bgnc.galleriportal.model;

import com.bgnc.galleriportal.enums.CarStatusType;
import com.bgnc.galleriportal.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "car")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Car extends BaseEntity{

    @Column(name = "plaka",nullable = false)
    private String plaka;
    @Column(name = "brand",nullable = false)
    private String brand;
    @Column(name = "model",nullable = false)
    private String model;
    @Column(name = "production_year",nullable = false)
    private Integer productionYear;
    @Column(name = "price",nullable = false)
    private BigDecimal price;
    @Column(name = "currency_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    @Column(name = "damage_price",nullable = false)
    private BigDecimal damagePrice;

    @Column(name = "car_status_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private CarStatusType carStatusType;

}
