package com.example.dailyneed_backened_repo.product.view.models;

import java.math.BigDecimal;

public class ProductRequest {
    private String category;
    private String item;
    private BigDecimal price;

    public ProductRequest() {
    }

    public ProductRequest(String category, String item, BigDecimal price) {

        this.category = category;
        this.item = item;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

