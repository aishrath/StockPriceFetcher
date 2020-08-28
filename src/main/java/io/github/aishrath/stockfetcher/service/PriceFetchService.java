package io.github.aishrath.stockfetcher.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aishrath.stockfetcher.util.RestKlient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PriceFetchService {
    private static final String BASE_URL = "https://cloud.iexapis.com/stable/stock/market/batch";
    private static final String TOKEN = "";

    public JsonNode fetch(String ticker) {
        log.info("Fetching Details for Ticker: " + ticker);

        Map<String, String> params = new HashMap<>();
        params.put("types", "quote");
        params.put("symbols", ticker);
        params.put("filter", "latestPrice,open,close,change,changePercent,marketCapf");
        params.put("token", TOKEN);

        var response = RestKlient.get(BASE_URL, params);
        log.info("Obtained response: " + response);

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(response.toString());
        } catch (JsonProcessingException e) {
            log.warn("Unable to read response!");
        }

        return null;
    }
}
