package com.example.prices.domain.port.out;

import com.example.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface LoadPricesPort {
    List<Price> findByProductBrandAndDate(long productId, long brandId, LocalDateTime date);
}
