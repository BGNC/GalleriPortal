package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.dto.*;
import com.bgnc.galleriportal.exception.BaseException;
import com.bgnc.galleriportal.exception.ErrorMessage;
import com.bgnc.galleriportal.exception.MessageType;
import com.bgnc.galleriportal.model.Car;
import com.bgnc.galleriportal.model.Gallerist;
import com.bgnc.galleriportal.model.GalleristCar;
import com.bgnc.galleriportal.repository.CarRepository;
import com.bgnc.galleriportal.repository.GalleristCarRepository;
import com.bgnc.galleriportal.repository.GalleristRepository;
import com.bgnc.galleriportal.service.IGalleristCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GalleristCarServiceImpl implements IGalleristCarService {


    private final GalleristCarRepository galleristCarRepository;
    private final CarRepository carRepository;
    private final GalleristRepository galleristRepository;

    private GalleristCar createGalleristCar(GalleristCarRequest galleristCarRequest) {
        Optional<Car> optCar = carRepository.findById(galleristCarRequest.getCar_id());
        if(optCar.isEmpty()){
            throw new BaseException(new ErrorMessage<>(MessageType.NO_RECORD_EXISTS,galleristCarRequest.getCar_id().toString()));

        }
        Optional<Gallerist> optGallerist = galleristRepository.findById(galleristCarRequest.getGallerist_id());
        if(optGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage<>(MessageType.NO_RECORD_EXISTS,galleristCarRequest.getGallerist_id().toString()));
        }
        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setGallerist(optGallerist.get());
        galleristCar.setCreateTime(new Date());
        galleristCar.setCar(optCar.get());

        return galleristCar;

    }

    @Override
    public GalleristCarResponse saveGalleristCar(GalleristCarRequest galleristCarRequest) {
        GalleristResponse galleristResponse = new GalleristResponse();
        AddressResponse addressResponse = new AddressResponse();
        CarResponse carResponse = new CarResponse();
        GalleristCarResponse galleristCarResponse = new GalleristCarResponse();

        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(galleristCarRequest));

        BeanUtils.copyProperties(savedGalleristCar, galleristCarResponse);

        BeanUtils.copyProperties(savedGalleristCar.getGallerist(), galleristResponse);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), addressResponse);
        BeanUtils.copyProperties(savedGalleristCar.getCar(), carResponse);


        galleristResponse.setAddress(addressResponse);
        galleristCarResponse.setCarResponse(carResponse);
        galleristCarResponse.setGallerist(galleristResponse);

        return galleristCarResponse;


    }
}
