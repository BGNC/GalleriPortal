package com.bgnc.galleriportal.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GalleristRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Long address_id;

}
