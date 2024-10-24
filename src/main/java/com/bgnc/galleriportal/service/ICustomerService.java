package com.bgnc.galleriportal.service;

import com.bgnc.galleriportal.dto.CustomerRequest;
import com.bgnc.galleriportal.dto.CustomerResponse;

public interface ICustomerService {
     CustomerResponse saveCustomer(CustomerRequest customerRequest);

}
