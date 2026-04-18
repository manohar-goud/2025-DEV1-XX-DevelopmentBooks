package com.manohar.developmentbooks.repository;

import com.manohar.developmentbooks.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}