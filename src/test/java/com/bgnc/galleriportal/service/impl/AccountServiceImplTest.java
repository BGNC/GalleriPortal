package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.dto.AccountRequest;
import com.bgnc.galleriportal.dto.AccountResponse;
import com.bgnc.galleriportal.enums.CurrencyType;
import com.bgnc.galleriportal.model.Account;
import com.bgnc.galleriportal.repository.AccountRepository;
import com.bgnc.galleriportal.service.IAccountService;
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


}
