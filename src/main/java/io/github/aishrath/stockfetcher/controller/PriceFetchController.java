package io.github.aishrath.stockfetcher.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.aishrath.stockfetcher.manager.PriceFetchManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PriceFetchController {
    final PriceFetchManager priceFetchManager;

    public PriceFetchController(PriceFetchManager priceFetchManager) {
        this.priceFetchManager = priceFetchManager;
    }

    @GetMapping("/stock")
    public JsonNode lookupTicker(@RequestParam String ticker) {
        if (StringUtils.isEmpty(ticker)) return generateBadRequestJsonNode();
        return priceFetchManager.grab(ticker);
    }

    private JsonNode generateBadRequestJsonNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();
        root.put("message", "Invalid ticker provided!");
        return root;
    }
}
