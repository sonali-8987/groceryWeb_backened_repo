package com.example.dailyneed_backened_repo.cart.view;

import com.example.dailyneed_backened_repo.cart.CartService;
import com.example.dailyneed_backened_repo.cart.repository.Cart;
import com.example.dailyneed_backened_repo.cart.view.models.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody CartRequest cartRequest) {

        cartService.add(cartRequest);
        return new ResponseEntity<>("Cart Item Successfully Added", HttpStatus.OK);

    }

    @GetMapping("/item")
    public ResponseEntity fetchAll() {
        List<Cart> cart = cartService.getCart();
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
