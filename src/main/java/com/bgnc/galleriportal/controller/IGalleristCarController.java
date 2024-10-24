package com.bgnc.galleriportal.controller;

import com.bgnc.galleriportal.dto.CustomerRequest;
import com.bgnc.galleriportal.dto.CustomerResponse;
import com.bgnc.galleriportal.dto.GalleristCarRequest;
import com.bgnc.galleriportal.dto.GalleristCarResponse;

public interface IGalleristCarController {
    RootEntity<GalleristCarResponse> saveGalleristCar(GalleristCarRequest galleristCarRequest);
}
