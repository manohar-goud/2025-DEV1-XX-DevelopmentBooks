package com.manohar.developmentbooks.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingServiceTest {

    private final PricingService pricingService = new PricingService();

    @Test
    @DisplayName("Empty basket should return 0.0")
    void shouldReturnZeroWhenBasketIsEmpty() {
        double result = pricingService.calculatePrice(Map.of());

        assertEquals(0.0, result);
    }

    @Test
    @DisplayName("single book should return 50.0")
    void shouldReturn50ForSingleBook() {
        double result = pricingService.calculatePrice(Map.of(1L, 1));

        assertEquals(50.0, result);
    }

    @Test
    @DisplayName("Two copies of same book should return 100.0")
    void shouldReturn100ForTwoCopiesOfSameBook() {
        double result = pricingService.calculatePrice(Map.of(1L, 2));

        assertEquals(100.0, result);
    }

    @Test
    @DisplayName("Two different books should return 95 EUR")
    void shouldReturn95ForTwoDifferentBooks() {
        double result = pricingService.calculatePrice(Map.of(1L, 1, 2L, 1));

        assertEquals(95.0, result);
    }

    @Test
    @DisplayName("Three different books should return 135 EUR")
    void shouldReturn135ForThreeDifferentBooks() {
        double result = pricingService.calculatePrice(Map.of(1L, 1, 2L, 1, 3L, 1));

        assertEquals(135.0, result);
    }

    @Test
    @DisplayName("Four different books should return 160 EUR")
    void shouldReturn160ForFourDifferentBooks() {
        double result = pricingService.calculatePrice(Map.of(1L, 1, 2L, 1, 3L, 1, 4L, 1));

        assertEquals(160, result);
    }

    @Test
    @DisplayName("Five different books should return 187.50 EUR")
    void shouldReturn187ForFiveDifferentBooks() {
        double result = pricingService.calculatePrice(Map.of(1L, 1, 2L, 1, 3L, 1, 4L, 1, 5L, 1));

        assertEquals(187.50, result);
    }

    @Test
    @DisplayName("two different  and one duplicate books should return 145 EUR")
    void shouldReturn145ForTwoDifferentBooksAndOneDuplicate() {
        double result = pricingService.calculatePrice(Map.of(4L, 2, 2L, 1));

        assertEquals(145.0, result);
    }

    @Test
    @DisplayName("Optimize sets from 5+3 to 4+4 and return 320.00")
    void optimisation() {
        double result = pricingService.calculatePrice(Map.of(1L, 2, 2L, 2, 3L, 2, 4L, 1, 5L, 1));

        assertEquals(320.00, result);
    }
}
