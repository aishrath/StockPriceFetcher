package io.github.aishrath.stockfetcher.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.aishrath.stockfetcher.controller.PriceFetchController;
import io.github.aishrath.stockfetcher.service.PriceFetchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PriceFetchManager {
    public final PriceFetchService priceFetchService;

    public PriceFetchManager(PriceFetchService priceFetchService) {
        this.priceFetchService = priceFetchService;
    }

    public JsonNode grab(String ticker) {
        var tickerDetails = priceFetchService.fetch(ticker);
        log.info("Fetched: " + tickerDetails);
        return tickerDetails.get("currentPrice");
    }
}
