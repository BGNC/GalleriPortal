package com.bgnc.galleriportal.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerResponse extends DtoBase{

    private String firstName;

    private String lastName;

    private String tckn;

    private Date birthOfDate;

    private AddressResponse address;

    private AccountResponse account;
}
