package com.example.product_category_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final RestTemplate restTemplate;
    @Value("${cb.url}")
    private String cbUrl;

    @Scheduled(cron = "* * 1 * * *")
    @Cacheable("currencies")
    public HashMap<String, String> getUsdCurrency() {
        ResponseEntity<HashMap> currencies = restTemplate.getForEntity(cbUrl + "?currency=USD", HashMap.class);
        HashMap<String, String> currencyMap = currencies.getBody();
        return currencyMap;
    }
}
