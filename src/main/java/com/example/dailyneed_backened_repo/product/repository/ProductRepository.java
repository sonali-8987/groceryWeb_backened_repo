package com.example.dailyneed_backened_repo.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByItem(String item);
    Optional<Product> findById(Long id);

    @Query(value = "select p.price  from product p where p.id = :id",nativeQuery = true)
     BigDecimal findPriceById(Long id);
}
