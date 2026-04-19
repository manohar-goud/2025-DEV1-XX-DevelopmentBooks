package com.manohar.developmentbooks.service;

import com.manohar.developmentbooks.entity.Discount;
import com.manohar.developmentbooks.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PricingService {

    private final DiscountRepository discountRepository;

    public double calculatePrice(Map<Long, Integer> bookQuantityMap) {
        if (bookQuantityMap.isEmpty()) {
            return 0.0;
        }

        List<Integer> uniqueSets = formUniqueBookSets(new ArrayList<>(bookQuantityMap.values()));
        applyFourSetOptimisation(uniqueSets);

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

    private Map<Integer, Double> loadDiscounts() {
        return discountRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Discount::getSetSize, Discount::getDiscountRate));
    }

    private double priceForEachSet(int setSize) {
        Map<Integer, Double> discounts = Map.of(1, 0.0, 2, 0.05, 3, 0.10, 4, 0.20, 5, 0.25);

        return setSize * 50 * (1 - discounts.getOrDefault(setSize, 0.0));
    }

    private void applyFourSetOptimisation(List<Integer> uniqueSets) {
        int swaps = Math.min(
                Collections.frequency(uniqueSets, 5),
                Collections.frequency(uniqueSets, 3)
        );

        for (int i = 0; i < swaps; i++) {
            uniqueSets.set(uniqueSets.indexOf(5), 4);
            uniqueSets.set(uniqueSets.indexOf(3), 4);
        }
    }
}
