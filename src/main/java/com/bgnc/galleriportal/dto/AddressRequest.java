package com.bgnc.galleriportal.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    @NotEmpty(message = "Street field is required")
    @Size(max = 100, message = "Street must not exceed 100 characters")
    private String street;

    @NotEmpty(message = "District field is required")
    @Size(max = 50, message = "District must not exceed 50 characters")
    private String district;

    @NotEmpty(message = "City field is required")
    @Size(max = 50, message = "City must not exceed 50 characters")
    private String city;

    @NotEmpty(message = "Neighborhood field is required")
    @Size(max = 50, message = "Neighborhood must not exceed 50 characters")
    private String neighborhood;
}
