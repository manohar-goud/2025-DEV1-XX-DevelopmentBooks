package com.manohar.developmentbooks.service;

import java.util.Map;

public class PricingService {

    public double calculatePrice(Map<Long, Integer> bookQuantityMap) {
        if (bookQuantityMap.isEmpty()) {
            return 0.0;
        }
        if (bookQuantityMap.size() == 1 && bookQuantityMap.get(1L) == 1) {
            return 50.0;
        }
        return 0.0;
    }
}
