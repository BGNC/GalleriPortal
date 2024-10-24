package com.bgnc.galleriportal.controller;

import com.bgnc.galleriportal.dto.CarRequest;
import com.bgnc.galleriportal.dto.CarResponse;

public interface ICarController {
    RootEntity<CarResponse> saveCar(CarRequest carRequest);
}
