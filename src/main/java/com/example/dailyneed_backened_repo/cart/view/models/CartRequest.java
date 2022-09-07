package com.example.dailyneed_backened_repo.cart.view.models;

public class CartRequest {

    private Long product_id;

    private Integer quantity;

    private Long user_id;

    public CartRequest(Long product_id, Integer quantity, Long user_id) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.user_id = user_id;
    }

    public CartRequest() {
    }

    public Long getProduct_id() {
        return product_id;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public Long getUser_id() {
        return user_id;
    }

}
