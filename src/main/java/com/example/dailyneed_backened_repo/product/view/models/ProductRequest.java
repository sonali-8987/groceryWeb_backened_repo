package com.example.dailyneed_backened_repo.product.view.models;

import com.example.dailyneed_backened_repo.product.repository.Product;

import java.math.BigDecimal;

public class ProductRequest {
    private String item;
    private BigDecimal price;
    private Long category_id;

    public ProductRequest() {
    }

    public ProductRequest(String item, BigDecimal price, Long category_id) {
        this.item = item;
        this.price = price;
        this.category_id = category_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
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

    public Product getProduct() {
        return new Product(item, price);
    }
}

