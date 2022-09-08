package com.example.dailyneed_backened_repo.bill.repository;

import com.example.dailyneed_backened_repo.bill.view.BillResponse;
import com.example.dailyneed_backened_repo.cart.repository.Cart;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;


    @Column(name = "price")
    private BigDecimal price;


    public Bill() {
    }

    public Bill(Cart cart, BigDecimal price) {
        this.cart = cart;
        this.price = price;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BillResponse constructBillResponse() {
        return new BillResponse(this.getId(), this.getCart().getId(), this.getPrice());
    }
}