package com.bgnc.galleriportal.controller.impl;

import com.bgnc.galleriportal.controller.ICurrencyRateController;
import com.bgnc.galleriportal.controller.RestBaseController;
import com.bgnc.galleriportal.controller.RootEntity;
import com.bgnc.galleriportal.dto.CurrencyRateResponse;
import com.bgnc.galleriportal.service.ICurrencyService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/v1/")
@RequiredArgsConstructor
public class RestCurrencyRateControllerImpl extends RestBaseController implements ICurrencyRateController {
    private final ICurrencyService currencyService;
    @GetMapping("/currency-rates")
    @Override
    public RootEntity<CurrencyRateResponse> getCurrencyRate(@RequestParam("startDate") String startDate,
                                                            @RequestParam("endDate") String endDate) {

        return ok(currencyService.getCurrencyRate(startDate, endDate));
    }
}
