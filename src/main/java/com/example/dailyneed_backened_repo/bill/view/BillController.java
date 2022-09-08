package com.example.dailyneed_backened_repo.bill.view;
import com.example.dailyneed_backened_repo.bill.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class BillController {

    @Autowired
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/bill")
    public List<BillResponse> fetchAll() {

        List<BillResponse> bill = billService.getBill();
        return bill;
    }

    @GetMapping("/total_bill")
    public BigDecimal fetchBill() {

        return billService.totalBill();

    }
}
