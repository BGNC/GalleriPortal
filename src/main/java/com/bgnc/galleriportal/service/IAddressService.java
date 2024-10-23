package com.bgnc.galleriportal.service;

import com.bgnc.galleriportal.dto.AddressRequest;
import com.bgnc.galleriportal.dto.AddressResponse;

public interface IAddressService {
    AddressResponse save(AddressRequest addressRequest);
}
