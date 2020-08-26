package io.github.aishrath.stockfetcher.controller;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.aishrath.stockfetcher.manager.PriceFetchManager;
import io.github.aishrath.stockfetcher.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class PriceFetchController {
    final PriceFetchManager priceFetchManager;

    public PriceFetchController(PriceFetchManager priceFetchManager) {
        this.priceFetchManager = priceFetchManager;
    }

    @GetMapping("/stock")
    public JsonNode lookupTicker(HttpServletRequest request, @RequestParam String ticker) {
        var properties = ApiUtils.getBeanProperties(request);
        log.info("Request Details: " + properties);
        if (StringUtils.isEmpty(ticker)) return ApiUtils.generateBadRequestJsonNode();
        return priceFetchManager.grab(ticker);
    }
}
