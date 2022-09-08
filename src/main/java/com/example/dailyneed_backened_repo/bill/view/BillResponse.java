package com.example.dailyneed_backened_repo.bill.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


public class BillResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private Long cart_id;

    @JsonProperty
    private BigDecimal price;

    public BillResponse(Long id, Long cart_id, BigDecimal price) {
        this.id = id;
        this.cart_id = cart_id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
