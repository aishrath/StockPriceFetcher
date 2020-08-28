package io.github.aishrath.stockfetcher.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.aishrath.stockfetcher.manager.PriceFetchManager;
import io.github.aishrath.stockfetcher.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class PriceFetchController {
    private final PriceFetchManager priceFetchManager;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PriceFetchController(PriceFetchManager priceFetchManager) {
        this.priceFetchManager = priceFetchManager;
    }

    @GetMapping("/stock")
    public JsonNode lookupTicker(@RequestParam String ticker) {
        if (StringUtils.isEmpty(ticker)) return ApiUtils.generateBadRequestJsonNode();
        ObjectNode root = objectMapper.createObjectNode();
        root.put("name", ticker);
        root.put("price", priceFetchManager.grab(ticker).toPrettyString());
        return root;
    }

    @GetMapping("/stocks")
    public List<JsonNode> lookupMultipleTickers(@RequestParam String tickers) {
        var details = priceFetchManager.grabMultiple(tickers);
        List<JsonNode> stocks = new ArrayList<>();
        var tickerList = tickers.split(",");
        int i = 0;
        for (var detail : details) {
            ObjectNode root = objectMapper.createObjectNode();
            root.put("name", tickerList[i]);
            root.put("price", detail.toPrettyString());
            stocks.add(root);
            i++;
        }

        return stocks;
    }
}
