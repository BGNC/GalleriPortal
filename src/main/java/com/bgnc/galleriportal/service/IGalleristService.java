package com.bgnc.galleriportal.service;

import com.bgnc.galleriportal.dto.GalleristRequest;
import com.bgnc.galleriportal.dto.GalleristResponse;
import com.bgnc.galleriportal.model.Gallerist;

public interface IGalleristService {

    GalleristResponse saveGallerist(GalleristRequest galleristRequest);
}
