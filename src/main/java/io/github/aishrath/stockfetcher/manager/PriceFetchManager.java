package io.github.aishrath.stockfetcher.manager;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.aishrath.stockfetcher.service.PriceFetchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PriceFetchManager {
    private final PriceFetchService priceFetchService;

    public PriceFetchManager(PriceFetchService priceFetchService) {
        this.priceFetchService = priceFetchService;
    }

    public JsonNode grab(String ticker) {
        var tickerDetails = priceFetchService.fetch(ticker);
        log.info("Fetched: " + tickerDetails);
        return tickerDetails.at("/" + ticker + "/quote" + "/latestPrice");
    }

    public List<JsonNode> grabMultiple(String tickers) {
        var tickerDetails = priceFetchService.fetch(tickers);
        List<JsonNode> prices = new ArrayList<>();
        log.info("Fetched: " + tickerDetails);
        var individualTickers = tickers.split(",");
        for (String ticker : individualTickers) {
            var detail = tickerDetails.at("/" + ticker + "/quote" + "/latestPrice");
            log.info("Adding: " + detail.asText());
            prices.add(detail);
        }
        return prices;
    }
}
