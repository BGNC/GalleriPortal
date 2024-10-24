package com.bgnc.galleriportal.controller;

import com.bgnc.galleriportal.dto.GalleristRequest;
import com.bgnc.galleriportal.dto.GalleristResponse;
import com.bgnc.galleriportal.model.Gallerist;

public interface IGalleristController {
    RootEntity<GalleristResponse> saveGallerist(GalleristRequest galleristRequest);
}
