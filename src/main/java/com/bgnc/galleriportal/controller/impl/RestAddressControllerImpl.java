package com.bgnc.galleriportal.controller.impl;

import com.bgnc.galleriportal.controller.IAddressController;
import com.bgnc.galleriportal.controller.RestBaseController;
import com.bgnc.galleriportal.controller.RootEntity;
import com.bgnc.galleriportal.dto.AddressRequest;
import com.bgnc.galleriportal.dto.AddressResponse;
import com.bgnc.galleriportal.service.IAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/v1/address")
@RequiredArgsConstructor
public class RestAddressControllerImpl extends RestBaseController implements IAddressController {

    private final IAddressService addressService;

    @PostMapping("/save")
    @Override
    public RootEntity<AddressResponse> save(@Valid @RequestBody AddressRequest addressRequest) {
        return ok(addressService.save(addressRequest));
    }
}
