package com.bgnc.galleriportal.dto;

import com.bgnc.galleriportal.enums.CarStatusType;
import com.bgnc.galleriportal.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CarRequest {

    @NotNull
    private String plaka;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private Integer productionYear;
    @NotNull
    private BigDecimal price;

    @NotNull
    private CurrencyType currencyType;

    @NotNull
    private BigDecimal damagePrice;

    @NotNull
    private CarStatusType carStatusType;
}
