package com.example.dailyneed_backened_repo.quantity;

import java.math.BigDecimal;

public enum Unit {
    KG(BigDecimal.ONE),
    G(new BigDecimal(1000));

    private final BigDecimal baseValue;

    Unit(BigDecimal baseValue) {
        this.baseValue = baseValue;
    }

    public BigDecimal convertToBase(BigDecimal value) {
        return value.divide(baseValue);
    }
}