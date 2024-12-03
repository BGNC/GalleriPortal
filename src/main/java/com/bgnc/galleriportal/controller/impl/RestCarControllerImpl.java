package com.bgnc.galleriportal.controller.impl;

import com.bgnc.galleriportal.controller.ICarController;
import com.bgnc.galleriportal.controller.RestBaseController;
import com.bgnc.galleriportal.controller.RootEntity;
import com.bgnc.galleriportal.dto.CarRequest;
import com.bgnc.galleriportal.dto.CarResponse;
import com.bgnc.galleriportal.service.ICarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/v1/car")
@RequiredArgsConstructor
public class RestCarControllerImpl extends RestBaseController implements ICarController {

    private final ICarService carService;



    @PostMapping("/saveCar")
    @Override
    public RootEntity<CarResponse> saveCar(@Valid @RequestBody CarRequest carRequest) {



        return ok(carService.saveCar(carRequest));
    }

}
