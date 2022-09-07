package com.example.dailyneed_backened_repo.cart.view;

import com.example.dailyneed_backened_repo.cart.CartService;
import com.example.dailyneed_backened_repo.cart.repository.Cart;
import com.example.dailyneed_backened_repo.cart.view.models.CartRequest;
import com.example.dailyneed_backened_repo.exceptions.CategoryNotFoundException;
import com.example.dailyneed_backened_repo.exceptions.ItemAlreadyExistException;
import com.example.dailyneed_backened_repo.exceptions.PriceCannotBeNegativeException;
import com.example.dailyneed_backened_repo.product.ProductService;
import com.example.dailyneed_backened_repo.product.view.models.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
