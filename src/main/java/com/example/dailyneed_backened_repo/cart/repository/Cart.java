package com.example.dailyneed_backened_repo.cart.repository;

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
    private BigDecimal quantity;

    @Column(name = "user_id")
    private Long user_id;

    public Cart() {
    }

    public Cart(Product product, BigDecimal quantity, Long user_id) {
        this.product = product;
        this.quantity = quantity;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public Long getUser_id() {
        return user_id;
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

    public BigDecimal getQuantity() {
        return this.quantity;
    }

}