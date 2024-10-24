package com.bgnc.galleriportal.dto;

import com.bgnc.galleriportal.enums.CurrencyType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponse extends DtoBase{


    private String accountNo;


    private String iban;


    private BigDecimal amount;


    private CurrencyType currencyType;
}
