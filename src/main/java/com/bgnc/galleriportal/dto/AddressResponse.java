package com.bgnc.galleriportal.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse extends DtoBase {

    @NotEmpty(message = "Street field is required")
    private String street;

    @NotEmpty(message = "District field is required")
    private String district;

    @NotEmpty(message = "City field is required")
    private String city;

    @NotEmpty(message = "Neighborhood field is required")
    private String neighborhood;
}
