package com.example.dailyneed_backened_repo.product.repository;

import com.example.dailyneed_backened_repo.category.repository.Category;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item")
    private String item;

    @Column(name = "price")
    private BigDecimal price;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
    }

    public Product(String item, BigDecimal price) {
        this.item = item;
        this.price = price;
    }

    public Product(String item, BigDecimal price, Category category) {

        this.item = item;
        this.price = price;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
