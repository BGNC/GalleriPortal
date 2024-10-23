package com.bgnc.galleriportal.controller;

import com.bgnc.galleriportal.dto.AddressRequest;
import com.bgnc.galleriportal.dto.AddressResponse;

public interface IAddressController {
    RootEntity<AddressResponse> save(AddressRequest addressRequest);
}
