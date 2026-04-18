package com.manohar.developmentbooks.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingServiceTest {

    @Test
    @DisplayName("Empty basket should return 0.0")
    void shouldReturnZeroWhenBasketIsEmpty() {
        PricingService pricingService = new PricingService();

        double result = pricingService.calculatePrice(Map.of());

        assertEquals(0.0, result);
    }

    @Test
    @DisplayName("single book should return 50.0")
    void shouldReturn50ForSingleBook() {
        PricingService pricingService = new PricingService();

        double result = pricingService.calculatePrice(Map.of(1L, 1));

        assertEquals(50.0, result);
    }

    @Test
    @DisplayName("Two copies of same book should return 100.0")
    void shouldReturn100ForTwoCopiesOfSameBook() {
        PricingService pricingService = new PricingService();

        double result = pricingService.calculatePrice(Map.of(1L, 2));

        assertEquals(100.0, result);
    }
}
