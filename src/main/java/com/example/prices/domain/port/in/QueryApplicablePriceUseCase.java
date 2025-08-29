package com.example.prices.domain.port.in;

import com.example.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface QueryApplicablePriceUseCase {
    Optional<Price> findApplicablePrice(LocalDateTime applicationDate, long productId, long brandId);
}
