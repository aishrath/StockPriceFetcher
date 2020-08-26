package io.github.aishrath.stockfetcher.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aishrath.stockfetcher.util.RestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PriceFetchService {
    private static final String BASE_URL = "https://cloud.iexapis.com/stable/stock/market/batch";
    private static final String TOKEN = "";

    public JsonNode fetch(String ticker) {
        RestClient restClient = new RestClient();
        restClient.setServer(BASE_URL);
        log.debug("Invoking call to: " + restClient.getServer());
        log.info("Fetching Details for Ticker: " + ticker);
        var response = restClient.get("?types=quote&symbols=" + ticker +
                "&filter=latestPrice,open,close,change,changePercent," +
                "marketCapf&token=" + TOKEN);
        log.info("Obtained response: " + response);

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(response);
        } catch (JsonProcessingException e) {
            log.warn("Unable to read response!");
        }

        return null;
    }
}
