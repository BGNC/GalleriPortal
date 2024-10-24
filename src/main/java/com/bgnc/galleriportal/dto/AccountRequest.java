package com.bgnc.galleriportal.dto;

import com.bgnc.galleriportal.enums.CurrencyType;
import jakarta.persistence.Column;
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
public class AccountRequest {
    @NotBlank(message = "Account number cannot be blank")
    private String accountNo;

    @NotBlank(message = "ıban number cannot be blank")
    private String iban;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Currency type cannot be null")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
}
