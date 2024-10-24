package com.bgnc.galleriportal.controller.impl;

import com.bgnc.galleriportal.controller.ICustomerController;
import com.bgnc.galleriportal.controller.RestBaseController;
import com.bgnc.galleriportal.controller.RootEntity;
import com.bgnc.galleriportal.dto.CustomerRequest;
import com.bgnc.galleriportal.dto.CustomerResponse;
import com.bgnc.galleriportal.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/v1/customer")
@RequiredArgsConstructor
public class RestCustomerControllerImpl extends RestBaseController implements ICustomerController {

    private final ICustomerService customerService;

    @PostMapping("/saveCustomer")
    @Override
    public RootEntity<CustomerResponse> saveCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return ok(customerService.saveCustomer(customerRequest));
    }
}
