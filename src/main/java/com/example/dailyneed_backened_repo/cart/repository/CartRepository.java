package com.example.dailyneed_backened_repo.cart.repository;

import com.example.dailyneed_backened_repo.product.repository.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
}
