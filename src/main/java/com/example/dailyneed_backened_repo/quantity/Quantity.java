package com.example.dailyneed_backened_repo.quantity;

import java.math.BigDecimal;
import java.util.Objects;

public class Quantity {
    private final BigDecimal magnitude;
    private final Unit unit;

    public Quantity(BigDecimal magnitude, Unit unit) {
        this.magnitude = magnitude;
        this.unit = unit;
    }
    public static Quantity createKilogram(BigDecimal magnitude) {
        return new Quantity(magnitude, Unit.KG);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Quantity quantity = (Quantity) obj;
        return (magnitude.compareTo(quantity.magnitude) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(magnitude);
    }
}


