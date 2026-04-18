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
}
