package com.example.dailyneed_backened_repo.category.repository;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String category;

    public Category() {
    }
    public Category(Long id, String category) {
        this.id = id;
        this.category = category;

    }

    public String getCategory() {
        return category;
    }

    public long getId() {
        return id;
    }
}
