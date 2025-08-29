package com.example.prices.adapter.out.jpa;

import com.example.prices.domain.model.Price;
import com.example.prices.domain.port.out.LoadPricesPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceJpaAdapter implements LoadPricesPort {

    private final PriceJpaRepository repository;

    public PriceJpaAdapter(PriceJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Price> findByProductBrandAndDate(long productId, long brandId, java.time.LocalDateTime date) {
        return repository.findApplicable(productId, brandId, date).stream()
                .map(e -> new Price(
                        e.getBrandId(),
                        e.getStartDate(),
                        e.getEndDate(),
                        e.getPriceList(),
                        e.getProductId(),
                        e.getPriority(),
                        e.getPrice(),
                        e.getCurr()
                ))
                .toList();
    }
}
