package com.example.dailyneed_backened_repo.bill;

import com.example.dailyneed_backened_repo.bill.repository.Bill;
import com.example.dailyneed_backened_repo.bill.repository.BillRepository;
import com.example.dailyneed_backened_repo.bill.view.BillResponse;
import com.example.dailyneed_backened_repo.cart.CartService;
import com.example.dailyneed_backened_repo.cart.repository.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private final BillRepository billRepository;

    @Autowired
    private final CartService cartService;

    public BillService(BillRepository billRepository, CartService cartService) {
        this.billRepository = billRepository;
        this.cartService = cartService;
    }

    public List<BillResponse> getBill() {
        billRepository.deleteAll();
        List<BillResponse> billResponses = new ArrayList<>();

        List<Cart> carts = cartService.getCart();

        for (int i = 0; i < carts.size(); i++) {
            Cart cart = carts.get(i);
            ;
            BigDecimal price = cartService.getPrice(cart.getId());
            Integer quantity = cart.getQuantity();
            BigDecimal totalPrice = price.multiply(new BigDecimal(quantity));
            Bill bill = new Bill(cart, totalPrice);
            billRepository.save(bill);
            billResponses.add(bill.constructBillResponse());
        }

        return billResponses;
    }

    public BigDecimal totalBill() {
        return billRepository.findTotalBill();
    }
}
