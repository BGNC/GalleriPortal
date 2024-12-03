package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.dto.CurrencyRateResponse;
import com.bgnc.galleriportal.exception.BaseException;
import com.bgnc.galleriportal.exception.ErrorMessage;
import com.bgnc.galleriportal.exception.MessageType;
import com.bgnc.galleriportal.service.ICurrencyService;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service


public class CurrencyServiceImpl implements ICurrencyService {

    @Value("${currency.api.key}")
    private String apiKey;

    @Override
    public CurrencyRateResponse getCurrencyRate(String startDate, String endDate) {
        String rootUrl = "https://evds2.tcmb.gov.tr/service/evds/";
        String series = "TP.DK.USD.A";
        String type = "json";
        String endPoint = rootUrl + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;

        HttpHeaders headers = new HttpHeaders();
        headers.set("key", apiKey);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<CurrencyRateResponse> exchange = restTemplate.exchange(endPoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRateResponse>() {});

            if (!exchange.getStatusCode().is2xxSuccessful()) {
                throw new BaseException(new ErrorMessage<>(MessageType.CURRENCY_RATE_IS_OCCURED, "Failed to fetch currency rate."));
            }

            return exchange.getBody();

        } catch (HttpClientErrorException e) {
            throw new BaseException(new ErrorMessage<>(MessageType.CLIENT_ERROR, "Client error: " + e.getMessage()));
        } catch (HttpServerErrorException e) {
            throw new BaseException(new ErrorMessage<>(MessageType.SERVER_ERROR, "Server error: " + e.getMessage()));
        } catch (RestClientException e) {
            throw new BaseException(new ErrorMessage<>(MessageType.CURRENCY_RATE_IS_OCCURED, "Error occurred while fetching currency data: " + e.getMessage()));
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage<>(MessageType.GENERAL_EXCEPTION, "An unexpected error occurred: " + e.getMessage()));
        }
    }

    @Override
    public CurrencyRateResponse getCurrencyRateFromBank(String startDate, String endDate) {
        return null;
    }


    @Scheduled(cron = "0 0 11 * * MON-FRI", zone = "Europe/Istanbul") // örn. saat 04:00'de çalışacak
    public void scheduledCurrencyRateFetch() {

        DayOfWeek today = LocalDate.now().getDayOfWeek();
        if (today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY) {
            return;
        }

        String todayDate = LocalDate.now().toString();
        getCurrencyRate(todayDate, todayDate);
    }
}
