package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.dto.AddressResponse;
import com.bgnc.galleriportal.dto.GalleristRequest;
import com.bgnc.galleriportal.dto.GalleristResponse;
import com.bgnc.galleriportal.exception.BaseException;
import com.bgnc.galleriportal.exception.ErrorMessage;
import com.bgnc.galleriportal.exception.MessageType;
import com.bgnc.galleriportal.model.Address;
import com.bgnc.galleriportal.model.Gallerist;
import com.bgnc.galleriportal.repository.AddressRepository;
import com.bgnc.galleriportal.repository.GalleristRepository;
import com.bgnc.galleriportal.service.IGalleristService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GalleristServiceImpl implements IGalleristService {

    private final GalleristRepository galleristRepository;
    private final AddressRepository addressRepository;

    private Gallerist createGallerist(GalleristRequest galleristRequest) {
        Optional<Address> optAddress = addressRepository.findById(galleristRequest.getAddress_id());
        if(optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage<>(MessageType.NO_RECORD_EXISTS,galleristRequest.getAddress_id().toString()));
        }

        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());

        BeanUtils.copyProperties(galleristRequest, gallerist);
        gallerist.setAddress(optAddress.get());

        return gallerist;

    }

    @Override
    public GalleristResponse saveGallerist(GalleristRequest galleristRequest) {
        Gallerist savedGallerist = galleristRepository.save(createGallerist(galleristRequest));
        AddressResponse addressResponse = new AddressResponse();
        GalleristResponse galleristResponse = new GalleristResponse();

        BeanUtils.copyProperties(savedGallerist, galleristResponse);
        BeanUtils.copyProperties(savedGallerist.getAddress(), addressResponse);

        galleristResponse.setAddress(addressResponse);

        return galleristResponse;
    }
}
