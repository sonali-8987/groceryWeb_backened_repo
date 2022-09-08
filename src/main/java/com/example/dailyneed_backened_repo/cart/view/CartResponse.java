package com.example.dailyneed_backened_repo.cart.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CartResponse {

    @JsonProperty
    private String item;

    @JsonProperty
    private Integer quantity;


    @JsonProperty
    private BigDecimal price;

    public CartResponse(String item, Integer quantity, BigDecimal price) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

}
