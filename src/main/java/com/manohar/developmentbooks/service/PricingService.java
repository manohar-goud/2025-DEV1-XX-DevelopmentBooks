package com.manohar.developmentbooks.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PricingService {

    public double calculatePrice(Map<Long, Integer> bookQuantityMap) {
        if (bookQuantityMap.isEmpty()) {
            return 0.0;
        }

        List<Integer> uniqueSets = formUniqueBookSets(new ArrayList<>(bookQuantityMap.values()));

        return uniqueSets.stream().mapToDouble(this::priceForEachSet).sum();
    }

    private List<Integer> formUniqueBookSets(List<Integer> copies) {
        List<Integer> remaining = new ArrayList<>(copies);
        List<Integer> uniqueSets = new ArrayList<>();

        while (remaining.stream().anyMatch(qty -> qty > 0)) {
            remaining.sort(Collections.reverseOrder());

            int groupSize = (int) remaining.stream().filter(qty -> qty > 0).count();
            uniqueSets.add(groupSize);

            for (int i = 0; i < groupSize; i++) {
                remaining.set(i, remaining.get(i) - 1);
            }
            remaining.removeIf(qty -> qty == 0);
        }
        return uniqueSets;
    }

    private double priceForEachSet(int setSize) {
        double discount = 0.0;
        if (setSize == 2) {
            discount = 0.05;
        } else if (setSize == 3) {
            discount = 0.10;
        } else if (setSize == 4) {
            discount = 0.20;
        }
        return setSize * 50 * (1 - discount);
    }
}
