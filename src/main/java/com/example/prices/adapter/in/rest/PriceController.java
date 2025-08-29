package com.example.prices.adapter.in.rest;

import com.example.prices.domain.model.Price;
import com.example.prices.domain.port.in.QueryApplicablePriceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PriceController {

    private final QueryApplicablePriceUseCase query;

    public PriceController(QueryApplicablePriceUseCase query) {
        this.query = query;
    }

    @GetMapping("/prices")
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam("applicationDate") String applicationDate,
            @RequestParam("productId") long productId,
            @RequestParam("brandId") long brandId
    ) {
        LocalDateTime date = parseDate(applicationDate);
        Optional<Price> result = query.findApplicablePrice(date, productId, brandId);
        return result.map(p -> ResponseEntity.ok(new PriceResponse(
                        p.productId(), p.brandId(), p.priceList(), p.startDate(), p.endDate(), p.price(), p.curr()
                )))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private LocalDateTime parseDate(String s) {
        try {
            return LocalDateTime.parse(s);
        } catch (Exception e) {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
            return LocalDateTime.parse(s, f);
        }
    }

    public record PriceResponse(
            long productId,
            long brandId,
            int priceList,
            LocalDateTime startDate,
            LocalDateTime endDate,
            java.math.BigDecimal price,
            String currency
    ) {}
}
