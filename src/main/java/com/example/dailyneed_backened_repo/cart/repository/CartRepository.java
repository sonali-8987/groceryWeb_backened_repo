package com.example.dailyneed_backened_repo.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "select * from cart c where c.product_id = :id",nativeQuery = true)
    Optional<Cart> findByProductId(Long id);
}
