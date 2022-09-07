package com.example.dailyneed_backened_repo.cart.repository;


import com.example.dailyneed_backened_repo.category.repository.Category;
import com.example.dailyneed_backened_repo.product.repository.Product;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @Column(name = "quantity")
    private Integer quantity;

    @Column(name="user_id")
    private Long user_id;

    public Cart() {
    }

    public Cart(Product product, Integer quantity, Long user_id) {
        this.product = product;
        this.quantity = quantity;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}