package com.manohar.developmentbooks.service;

import java.util.Map;

public class PricingService {

    public double calculatePrice(Map<Long, Integer> bookQuantityMap) {
        if (bookQuantityMap.isEmpty()) {
            return 0.0;
        }
        int totalBooks = bookQuantityMap.values().stream()
                .mapToInt(Integer::intValue).sum();

        return totalBooks * 50.0;
    }
}
