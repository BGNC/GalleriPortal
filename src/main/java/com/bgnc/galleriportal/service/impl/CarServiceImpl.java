package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.dto.CarRequest;
import com.bgnc.galleriportal.dto.CarResponse;
import com.bgnc.galleriportal.model.Car;
import com.bgnc.galleriportal.repository.CarRepository;
import com.bgnc.galleriportal.repository.GenericRepository;
import com.bgnc.galleriportal.service.ICarService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CarServiceImpl extends GenericServiceImpl<Car,Long> implements ICarService  {



    public CarServiceImpl(GenericRepository<Car,Long> carRepository) {
        super(carRepository);

    }

    private Car createCar(CarRequest carRequest){
        Car car = new Car();
        car.setCreateTime(new Date());

        BeanUtils.copyProperties(carRequest, car);
        return car;

    }



    @Override
    public CarResponse saveCar(CarRequest carRequest) {

        CarResponse carResponse = new CarResponse();
        Car car = save(createCar(carRequest));
        BeanUtils.copyProperties(car, carResponse);
        return carResponse;
    }


}
