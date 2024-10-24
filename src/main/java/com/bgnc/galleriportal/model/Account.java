package com.bgnc.galleriportal.model;

import com.bgnc.galleriportal.enums.CurrencyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account extends BaseEntity {

    @NotBlank(message = "Account number cannot be blank")
    @Column(name = "account_no")
    private String accountNo;

    @NotBlank(message = "ıban number cannot be blank")
    @Column(name = "iban")
    private String iban;


    @NotNull(message = "Amount cannot be null")
    @Column(name = "amount")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Currency type cannot be null")
    @Column(name = "currency_type")

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
}
