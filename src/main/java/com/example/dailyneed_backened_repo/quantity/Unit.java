package com.example.dailyneed_backened_repo.quantity;

import java.math.BigDecimal;

public enum Unit {
    KG(BigDecimal.ONE),
    G(new BigDecimal(1000));

    private final BigDecimal magnitude;

    Unit(BigDecimal magnitude) {
        this.magnitude = magnitude;
    }

    public BigDecimal convertToBase(BigDecimal value) {
        return value.divide(magnitude);
    }
}