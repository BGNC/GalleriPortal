package com.bgnc.galleriportal.service;


import com.bgnc.galleriportal.dto.CarRequest;
import com.bgnc.galleriportal.dto.CarResponse;


public interface ICarService {

    CarResponse saveCar(CarRequest carRequest);
}
