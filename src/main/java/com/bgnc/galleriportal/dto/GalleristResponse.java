package com.bgnc.galleriportal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GalleristResponse extends DtoBase {

    private String firstName;

    private String lastName;

    private AddressResponse address;


}
