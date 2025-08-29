package com.example.prices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PricesApplicationIT {

    @Autowired
    MockMvc mockMvc;

    private void expect(String date, int priceList, BigDecimal price) throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("applicationDate", date)
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(priceList))
                .andExpect(jsonPath("$.price").value(price))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1));
    }

    @Test
    void test1_2020_06_14_10_00() throws Exception {
        expect("2020-06-14-10.00.00", 1, BigDecimal.valueOf(35.50));
    }

    @Test
    void test2_2020_06_14_16_00() throws Exception {
        expect("2020-06-14-16.00.00", 2, BigDecimal.valueOf(25.45));
    }

    @Test
    void test3_2020_06_14_21_00() throws Exception {
        expect("2020-06-14-21.00.00", 1, BigDecimal.valueOf(35.50));
    }

    @Test
    void test4_2020_06_15_10_00() throws Exception {
        expect("2020-06-15-10.00.00", 3, BigDecimal.valueOf(30.50));
    }

    @Test
    void test5_2020_06_16_21_00() throws Exception {
        expect("2020-06-16-21.00.00", 4, BigDecimal.valueOf(38.95));
    }
}
