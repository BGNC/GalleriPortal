package com.bgnc.galleriportal.dto;

import com.bgnc.galleriportal.enums.CarStatusType;
import com.bgnc.galleriportal.enums.CurrencyType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CarResponse extends DtoBase{

    private String plaka;

    private String brand;

    private String model;

    private Integer productionYear;

    private BigDecimal price;


    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;
}
