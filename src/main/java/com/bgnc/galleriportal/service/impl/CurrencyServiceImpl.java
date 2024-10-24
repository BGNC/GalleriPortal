package com.bgnc.galleriportal.service.impl;

import com.bgnc.galleriportal.dto.CurrencyRateResponse;
import com.bgnc.galleriportal.exception.BaseException;
import com.bgnc.galleriportal.exception.ErrorMessage;
import com.bgnc.galleriportal.exception.MessageType;
import com.bgnc.galleriportal.service.ICurrencyService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyServiceImpl implements ICurrencyService {


    @Override
    public CurrencyRateResponse getCurrencyRate(String startDate, String endDate) {
    //    series=TP.DK.USD.A&startDate=24-09-2024&endDate=24-09-2024&type=json
        String rootUrl = "https://evds2.tcmb.gov.tr/service/evds/";
        String series="TP.DK.USD.A";
        String type="json";

        String endPoint = rootUrl+"series="+series+"&startDate="+startDate+"&endDate="+endDate+"&type="+type;

        HttpHeaders headers = new HttpHeaders();
        headers.set("key","k5LgPcyC26");

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<CurrencyRateResponse> exchange = restTemplate.exchange(endPoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRateResponse>() {
            });

            if (!exchange.getStatusCode().is2xxSuccessful()) {
                throw new BaseException(new ErrorMessage<>(MessageType.CURRENCY_RATE_IS_OCCURED, "Failed to fetch currency rate."));
            }

            return exchange.getBody();

        } catch (HttpClientErrorException e) {
            // 4xx hataları için
            throw new BaseException(new ErrorMessage<>(MessageType.CLIENT_ERROR, "Client error: " + e.getMessage()));
        } catch (HttpServerErrorException e) {
            // 5xx hataları için
            throw new BaseException(new ErrorMessage<>(MessageType.SERVER_ERROR, "Server error: " + e.getMessage()));
        } catch (RestClientException e) {
            // Genel RestTemplate hataları için
            throw new BaseException(new ErrorMessage<>(MessageType.CURRENCY_RATE_IS_OCCURED, "Error occurred while fetching currency data: " + e.getMessage()));
        } catch (Exception e) {
            // Diğer beklenmeyen hatalar için
            throw new BaseException(new ErrorMessage<>(MessageType.GENERAL_EXCEPTION, "An unexpected error occurred: " + e.getMessage()));
        }

    }
}
