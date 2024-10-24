package com.bgnc.galleriportal.controller.impl;

import com.bgnc.galleriportal.controller.IGalleristController;
import com.bgnc.galleriportal.controller.RestBaseController;
import com.bgnc.galleriportal.controller.RootEntity;
import com.bgnc.galleriportal.dto.GalleristRequest;
import com.bgnc.galleriportal.dto.GalleristResponse;
import com.bgnc.galleriportal.service.IGalleristService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/api/v1/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IGalleristController {

    private final IGalleristService galleristService;

    @PostMapping("/saveGallerist")
    @Override
    public RootEntity<GalleristResponse> saveGallerist(@Valid @RequestBody GalleristRequest galleristRequest) {
        return ok(galleristService.saveGallerist(galleristRequest));
    }

}
