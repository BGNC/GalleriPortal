package com.bgnc.galleriportal.controller.impl;

import com.bgnc.galleriportal.controller.IGalleristCarController;
import com.bgnc.galleriportal.controller.RestBaseController;
import com.bgnc.galleriportal.controller.RootEntity;
import com.bgnc.galleriportal.dto.GalleristCarRequest;
import com.bgnc.galleriportal.dto.GalleristCarResponse;
import com.bgnc.galleriportal.service.IGalleristCarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/v1/galleristcar")
@RequiredArgsConstructor
public class RestGalleristCarControllerImpl extends RestBaseController implements IGalleristCarController {
    private final IGalleristCarService galleristCarService;

    @PostMapping("/saveGalleristCar")
    @Override
    public RootEntity<GalleristCarResponse> saveGalleristCar(@Valid @RequestBody GalleristCarRequest galleristCarRequest) {
        return ok(galleristCarService.saveGalleristCar(galleristCarRequest));
    }
}
