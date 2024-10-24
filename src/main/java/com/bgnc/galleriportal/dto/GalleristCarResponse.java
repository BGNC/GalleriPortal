package com.bgnc.galleriportal.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GalleristCarResponse extends DtoBase{

    private GalleristResponse gallerist;

    private CarResponse carResponse;

}
