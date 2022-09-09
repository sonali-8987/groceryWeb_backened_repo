package com.example.dailyneed_backened_repo.cart.view.models;

import com.example.dailyneed_backened_repo.quantity.Unit;

import java.math.BigDecimal;

public class CartRequest {

    private Long product_id;

    private BigDecimal magnitude;

    private String unit;

    private Long user_id;

    public CartRequest() {
    }

    public CartRequest(Long product_id, BigDecimal magnitude, String unit,Long user_id) {
        this.product_id = product_id;
        this.magnitude = magnitude;
        this.unit = unit;
        this.user_id = user_id;
    }

    public String getUnit() {
        return unit;
    }


    public Long getProduct_id() {
        return product_id;
    }


    public BigDecimal getMagnitude() {
        return magnitude;
    }

    public Long getUser_id() {
        return user_id;
    }

}
