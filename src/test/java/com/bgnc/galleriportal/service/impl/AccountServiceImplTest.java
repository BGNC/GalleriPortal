package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.dto.AccountRequest;
import com.bgnc.galleriportal.dto.AccountResponse;
import com.bgnc.galleriportal.enums.CurrencyType;
import com.bgnc.galleriportal.model.Account;
import com.bgnc.galleriportal.repository.AccountRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSaveAccountWithValidRequest_ShouldReturnSavedAccountResponse() {

        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountNo("12345678");
        accountRequest.setIban("TR123456789012345678901234");
        accountRequest.setAmount(BigDecimal.valueOf(100.50));
        accountRequest.setCurrencyType(CurrencyType.USD);


        Account account = new Account();
        account.setAccountNo("12345678");
        account.setIban("TR123456789012345678901234");
        account.setAmount(BigDecimal.valueOf(100.50));
        account.setCurrencyType(CurrencyType.USD);


        when(accountRepository.save(account)).thenReturn(account);


        AccountResponse accountResponse = accountService.saveAccount(accountRequest);

        assertEquals(account.getAccountNo(), accountResponse.getAccountNo());
        assertEquals(account.getIban(), accountResponse.getIban());
        assertEquals(account.getAmount(), accountResponse.getAmount());
        assertEquals(account.getCurrencyType(), accountResponse.getCurrencyType());
    }

    @Test
    void testSaveAccountWithInvalidRequest_ShouldThrowValidationErrors() {

        AccountRequest invalidRequest = new AccountRequest();
        invalidRequest.setAccountNo("");
        invalidRequest.setIban("");
        invalidRequest.setAmount(BigDecimal.valueOf(-100));
        invalidRequest.setCurrencyType(null);


        Set<ConstraintViolation<AccountRequest>> violations = validator.validate(invalidRequest);


        assertEquals(4, violations.size());


        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Account number cannot be blank")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("ıban number cannot be blank")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Amount must be greater than 0")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Currency type cannot be null")));
    }

    @Test
    void testValidation_ShouldFail_WhenAccountNumberIsBlank() {

        AccountRequest invalidRequest = new AccountRequest();
        invalidRequest.setAccountNo("");
        invalidRequest.setIban("TR123456789012345678901234");
        invalidRequest.setAmount(BigDecimal.valueOf(100));
        invalidRequest.setCurrencyType(CurrencyType.USD);

        Set<ConstraintViolation<AccountRequest>> violations = validator.validate(invalidRequest);


        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Account number cannot be blank")));
    }

    @Test
    void testValidation_ShouldFail_WhenIbanIsBlank() {

        AccountRequest invalidRequest = new AccountRequest();
        invalidRequest.setAccountNo("12345678");
        invalidRequest.setIban(""); // Invalid blank IBAN
        invalidRequest.setAmount(BigDecimal.valueOf(100));
        invalidRequest.setCurrencyType(CurrencyType.USD);


        Set<ConstraintViolation<AccountRequest>> violations = validator.validate(invalidRequest);

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("ıban number cannot be blank")));
    }

    @Test
    void testValidation_ShouldFail_WhenAmountIsNegative() {

        AccountRequest invalidRequest = new AccountRequest();
        invalidRequest.setAccountNo("12345678");
        invalidRequest.setIban("TR123456789012345678901234");
        invalidRequest.setAmount(BigDecimal.valueOf(-100));
        invalidRequest.setCurrencyType(CurrencyType.USD);

        Set<ConstraintViolation<AccountRequest>> violations = validator.validate(invalidRequest);


        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Amount must be greater than 0")));
    }

    @Test
    void testValidation_ShouldFail_WhenCurrencyTypeIsNull() {

        AccountRequest invalidRequest = new AccountRequest();
        invalidRequest.setAccountNo("12345678");
        invalidRequest.setIban("TR123456789012345678901234");
        invalidRequest.setAmount(BigDecimal.valueOf(100));
        invalidRequest.setCurrencyType(null);

        Set<ConstraintViolation<AccountRequest>> violations = validator.validate(invalidRequest);

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Currency type cannot be null")));
    }
}
