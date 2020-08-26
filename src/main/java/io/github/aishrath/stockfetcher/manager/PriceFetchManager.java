package io.github.aishrath.stockfetcher.manager;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.aishrath.stockfetcher.service.PriceFetchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PriceFetchManager {
    final PriceFetchService priceFetchService;

    public PriceFetchManager(PriceFetchService priceFetchService) {
        this.priceFetchService = priceFetchService;
    }

    public JsonNode grab(String ticker) {

    }
}
