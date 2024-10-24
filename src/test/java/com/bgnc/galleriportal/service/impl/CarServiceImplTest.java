package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.dto.CarRequest;
import com.bgnc.galleriportal.dto.CarResponse;
import com.bgnc.galleriportal.enums.CarStatusType;
import com.bgnc.galleriportal.enums.CurrencyType;
import com.bgnc.galleriportal.model.Car;
import com.bgnc.galleriportal.repository.CarRepository;
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

class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSaveCarWithValidRequest_ShouldReturnSavedCarResponse() {
        // Arrange: Create valid car request
        CarRequest carRequest = new CarRequest();
        carRequest.setPlaka("34ABC123");
        carRequest.setBrand("Toyota");
        carRequest.setModel("Corolla");
        carRequest.setProductionYear(2020);
        carRequest.setPrice(BigDecimal.valueOf(15000));
        carRequest.setCurrencyType(CurrencyType.USD);
        carRequest.setDamagePrice(BigDecimal.valueOf(500));
        carRequest.setCarStatusType(CarStatusType.SALEABLE);

        // Arrange: Prepare Car entity
        Car car = new Car();
        car.setPlaka("34ABC123");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setProductionYear(2020);
        car.setPrice(BigDecimal.valueOf(15000));
        car.setCurrencyType(CurrencyType.USD);
        car.setDamagePrice(BigDecimal.valueOf(500));
        car.setCarStatusType(CarStatusType.SALEABLE);

        // Act: Mock repository save method
        when(carRepository.save(car)).thenReturn(car);

        // Act: Call the service method
        CarResponse carResponse = carService.saveCar(carRequest);

        // Assert: Validate response fields
        assertEquals(car.getPlaka(), carResponse.getPlaka());
        assertEquals(car.getBrand(), carResponse.getBrand());
        assertEquals(car.getModel(), carResponse.getModel());
        assertEquals(car.getProductionYear(), carResponse.getProductionYear());
        assertEquals(car.getPrice(), carResponse.getPrice());
        assertEquals(car.getCurrencyType(), carResponse.getCurrencyType());
        assertEquals(car.getDamagePrice(), carResponse.getDamagePrice());
        assertEquals(car.getCarStatusType(), carResponse.getCarStatusType());
    }

    @Test
    void testSaveCarWithInvalidRequest_ShouldThrowValidationErrors() {
        // Arrange: Create invalid car request
        CarRequest invalidRequest = new CarRequest();
        invalidRequest.setPlaka(null); // Invalid null plaka
        invalidRequest.setBrand(null); // Invalid null brand
        invalidRequest.setModel(null); // Invalid null model
        invalidRequest.setProductionYear(null); // Invalid null production year
        invalidRequest.setPrice(BigDecimal.valueOf(-15000)); // Invalid negative price
        invalidRequest.setCurrencyType(null); // Null currency type
        invalidRequest.setDamagePrice(BigDecimal.valueOf(-500)); // Invalid negative damage price
        invalidRequest.setCarStatusType(null); // Null car status type

        // Act: Validate request using Validator
        Set<ConstraintViolation<CarRequest>> violations = validator.validate(invalidRequest);

        // Assert: Check number of violations
        assertEquals(8, violations.size());

        // Assert: Check specific validation messages
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("must not be null")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("must be greater than 0")));
    }

    @Test
    void testValidation_ShouldFail_WhenPlakaIsNull() {
        // Arrange: Create request with null plaka
        CarRequest invalidRequest = new CarRequest();
        invalidRequest.setPlaka(null); // Invalid null plaka
        invalidRequest.setBrand("Toyota");
        invalidRequest.setModel("Corolla");
        invalidRequest.setProductionYear(2020);
        invalidRequest.setPrice(BigDecimal.valueOf(15000));
        invalidRequest.setCurrencyType(CurrencyType.USD);
        invalidRequest.setDamagePrice(BigDecimal.valueOf(500));
        invalidRequest.setCarStatusType(CarStatusType.SALEABLE);

        // Act: Validate request
        Set<ConstraintViolation<CarRequest>> violations = validator.validate(invalidRequest);

        // Assert: Check for plaka violation
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("must not be null")));
    }

    @Test
    void testValidation_ShouldFail_WhenPriceIsNegative() {
        // Arrange: Create request with negative price
        CarRequest invalidRequest = new CarRequest();
        invalidRequest.setPlaka("34ABC123");
        invalidRequest.setBrand("Toyota");
        invalidRequest.setModel("Corolla");
        invalidRequest.setProductionYear(2020);
        invalidRequest.setPrice(BigDecimal.valueOf(-15000)); // Invalid negative price
        invalidRequest.setCurrencyType(CurrencyType.USD);
        invalidRequest.setDamagePrice(BigDecimal.valueOf(500));
        invalidRequest.setCarStatusType(CarStatusType.SALEABLE);

        // Act: Validate request
        Set<ConstraintViolation<CarRequest>> violations = validator.validate(invalidRequest);

        // Assert: Check for price violation
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("must be greater than 0")));
    }

    @Test
    void testValidation_ShouldFail_WhenCarStatusTypeIsNull() {
        // Arrange: Create request with null car status type
        CarRequest invalidRequest = new CarRequest();
        invalidRequest.setPlaka("34ABC123");
        invalidRequest.setBrand("Toyota");
        invalidRequest.setModel("Corolla");
        invalidRequest.setProductionYear(2020);
        invalidRequest.setPrice(BigDecimal.valueOf(15000));
        invalidRequest.setCurrencyType(CurrencyType.USD);
        invalidRequest.setDamagePrice(BigDecimal.valueOf(500));
        invalidRequest.setCarStatusType(null); // Invalid null car status type

        // Act: Validate request
        Set<ConstraintViolation<CarRequest>> violations = validator.validate(invalidRequest);

        // Assert: Check for car status type violation
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("must not be null")));
    }
}
