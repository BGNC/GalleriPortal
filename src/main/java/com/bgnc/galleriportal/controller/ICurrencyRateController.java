package com.bgnc.galleriportal.controller;

import com.bgnc.galleriportal.dto.CurrencyRateResponse;

public interface ICurrencyRateController {
    RootEntity<CurrencyRateResponse> getCurrencyRate(String startDate,String endDate);
}
