package com.example.prices.adapter.out.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p " +
           "WHERE p.productId = :productId " +
           "AND p.brandId = :brandId " +
           "AND :date BETWEEN p.startDate AND p.endDate " +
           "ORDER BY p.priority DESC")
    List<PriceEntity> findApplicable(@Param("productId") long productId,
                                     @Param("brandId") long brandId,
                                     @Param("date") LocalDateTime date);
}
