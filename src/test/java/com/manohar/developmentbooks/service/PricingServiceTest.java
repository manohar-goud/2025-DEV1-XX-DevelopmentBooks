package com.manohar.developmentbooks.service;

import com.manohar.developmentbooks.entity.Discount;
import com.manohar.developmentbooks.repository.DiscountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PricingServiceTest {

    @Mock
    private DiscountRepository discountRepository;
    @InjectMocks
    private PricingService pricingService;

    @BeforeEach
    void setUp() {
        when(discountRepository.findAll()).thenReturn(List.of(
                new Discount(1, 0.00),
                new Discount(2, 0.05),
                new Discount(3, 0.10),
                new Discount(4, 0.20),
                new Discount(5, 0.25)
        ));
    }

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

    @Test
    @DisplayName("Greedy-trap single swap: [5,5,3] → [5,4,4] returns 507.50 EUR")
    void greedyTrapOneSwap() {
        double result = pricingService.calculatePrice(Map.of(1L, 3, 2L, 3, 3L, 3, 4L, 2, 5L, 2));

        assertEquals(507.50, result);
    }


    @Test
    @DisplayName("Greedy-trap two swaps: [5,5,3,3] → [4,4,4,4] returns 640 EUR")
    void greedyTrapTwoSwap() {
        double result = pricingService.calculatePrice(Map.of(1L, 4, 2L, 4, 3L, 4, 4L, 2, 5L, 2));

        assertEquals(640.00, result);
    }
}
