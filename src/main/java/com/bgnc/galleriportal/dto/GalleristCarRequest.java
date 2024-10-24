package com.bgnc.galleriportal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GalleristCarRequest {

    @NotNull
    private Long gallerist_id;

    @NotNull
    private Long car_id;
}
