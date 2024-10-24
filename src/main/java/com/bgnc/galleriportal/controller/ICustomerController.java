package com.bgnc.galleriportal.controller;


import com.bgnc.galleriportal.dto.CustomerRequest;
import com.bgnc.galleriportal.dto.CustomerResponse;

public interface ICustomerController {
    RootEntity<CustomerResponse> saveCustomer(CustomerRequest customerRequest);
}
