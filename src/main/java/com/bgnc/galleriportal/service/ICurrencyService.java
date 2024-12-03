package com.bgnc.galleriportal.service;

import com.bgnc.galleriportal.dto.CurrencyRateResponse;

public interface ICurrencyService {
    CurrencyRateResponse getCurrencyRate(String startDate,String endDate);
    CurrencyRateResponse getCurrencyRateFromBank(String startDate,String endDate);
}
