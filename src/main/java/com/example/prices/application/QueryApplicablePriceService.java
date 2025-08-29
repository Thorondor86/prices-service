package com.example.prices.application;

import com.example.prices.domain.model.Price;
import com.example.prices.domain.port.in.QueryApplicablePriceUseCase;
import com.example.prices.domain.port.out.LoadPricesPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Service
public class QueryApplicablePriceService implements QueryApplicablePriceUseCase {

    private final LoadPricesPort loadPricesPort;

    public QueryApplicablePriceService(LoadPricesPort loadPricesPort) {
        this.loadPricesPort = loadPricesPort;
    }

    @Override
    public Optional<Price> findApplicablePrice(LocalDateTime applicationDate, long productId, long brandId) {
        return loadPricesPort.findByProductBrandAndDate(productId, brandId, applicationDate)
                .stream()
                .max(Comparator.comparingInt(Price::priority));
    }
}
