package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.dto.AddressRequest;
import com.bgnc.galleriportal.dto.AddressResponse;
import com.bgnc.galleriportal.model.Address;
import com.bgnc.galleriportal.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;

    private AddressRequest addressRequest;
    private Address savedAddress;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        addressRequest = new AddressRequest("Main St", "Downtown", "Metropolis", "Central");
        savedAddress = new Address();
        savedAddress.setId(1L);
        savedAddress.setStreet("Main St");
        savedAddress.setDistrict("Downtown");
        savedAddress.setCity("Metropolis");
        savedAddress.setNeighborhood("Central");
    }

    @Test
    public void testSaveAddress() {
        // Arrange
        when(addressRepository.save(addressService.createAddress(addressRequest))).thenReturn(savedAddress);

        // Act
        AddressResponse addressResponse = addressService.save(addressRequest);

        // Assert
        assertEquals(savedAddress.getStreet(), addressResponse.getStreet());
        assertEquals(savedAddress.getDistrict(), addressResponse.getDistrict());
        assertEquals(savedAddress.getCity(), addressResponse.getCity());
        assertEquals(savedAddress.getNeighborhood(), addressResponse.getNeighborhood());
    }

    @Test
    public void testSaveAddress_whenAddressRepositoryThrowsException() {
        // Arrange
        when(addressRepository.save(any(Address.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            addressService.save(addressRequest);
        });

        assertEquals("Database error", thrown.getMessage());
    }


}
