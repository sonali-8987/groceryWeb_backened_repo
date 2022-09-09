package com.example.dailyneed_backened_repo.cart.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CartResponse {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String item;

    @JsonProperty
    private BigDecimal quantity;

    @JsonProperty
    private BigDecimal price;

    public CartResponse(Long id, String item, BigDecimal quantity, BigDecimal price) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

}
