package com.example.dailyneed_backened_repo.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {

    @Query(value = "select sum(price) from bill",nativeQuery = true)
    BigDecimal findTotalBill();
}
