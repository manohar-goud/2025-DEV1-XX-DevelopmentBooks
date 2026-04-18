package com.manohar.developmentbooks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "discount")
public class Discount {

    @Id
    private int setSize;

    @Column(nullable = false)
    private double discountRate;

    protected Discount() {
    }
}
