package com.bgnc.galleriportal.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerRequest {

    @NotBlank(message = "First name cannot be blank")

    private String firstName;

    @NotBlank(message = "Last name cannot be blank")

    private String lastName;


    @NotBlank(message = "TCKN cannot be blank")

    @Pattern(regexp = "^[0-9]{11}$", message = "TCKN must be exactly 11 digits")
    private String tckn;

    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Birth date must be in the past")
    private Date birthOfDate;

    private Long address_id;

    private Long account_id;
}
