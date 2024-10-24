package com.bgnc.galleriportal.service;

import com.bgnc.galleriportal.dto.GalleristCarRequest;
import com.bgnc.galleriportal.dto.GalleristCarResponse;


public interface IGalleristCarService {

    GalleristCarResponse saveGalleristCar(GalleristCarRequest galleristCarRequest);
}
