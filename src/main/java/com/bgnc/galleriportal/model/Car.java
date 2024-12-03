package com.bgnc.galleriportal.model;

import com.bgnc.galleriportal.enums.CarStatusType;
import com.bgnc.galleriportal.enums.CurrencyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String plaka;
    @Column(name = "brand",nullable = false)
    @NotNull
    private String brand;
    @Column(name = "model",nullable = false)
    @NotNull
    private String model;
    @Column(name = "production_year",nullable = false)
    @NotNull
    private Integer productionYear;

    @Column(name = "price",nullable = false)
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "The value must be greater than 0")
    private BigDecimal price;
    @Column(name = "currency_type",nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private CurrencyType currencyType;
    @Column(name = "damage_price",nullable = false)
    @NotNull
    private BigDecimal damagePrice;

    @Column(name = "car_status_type",nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private CarStatusType carStatusType;

}
