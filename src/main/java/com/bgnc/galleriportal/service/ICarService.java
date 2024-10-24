package com.bgnc.galleriportal.service;


import com.bgnc.galleriportal.dto.CarRequest;
import com.bgnc.galleriportal.dto.CarResponse;
import com.bgnc.galleriportal.model.Car;

public interface ICarService {

    CarResponse saveCar(CarRequest carRequest);
}
