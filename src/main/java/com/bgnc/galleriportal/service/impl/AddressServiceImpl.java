package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.dto.AddressRequest;
import com.bgnc.galleriportal.dto.AddressResponse;
import com.bgnc.galleriportal.model.Address;
import com.bgnc.galleriportal.repository.AddressRepository;
import com.bgnc.galleriportal.service.IAddressService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;

    public Address createAddress(AddressRequest addressRequest){
        Address address = new Address();
        address.setCreateTime(new Date());
        BeanUtils.copyProperties(addressRequest, address);
        return  address;
    }
    @Override
    public AddressResponse save(AddressRequest addressRequest) {

        AddressResponse addressResponse = new AddressResponse();
        Address savedAddress = addressRepository.save( createAddress(addressRequest));

        BeanUtils.copyProperties(savedAddress, addressResponse);

        return addressResponse;
    }
}
