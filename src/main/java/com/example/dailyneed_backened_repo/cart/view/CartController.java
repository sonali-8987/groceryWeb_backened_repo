package com.example.dailyneed_backened_repo.cart.view;

import com.example.dailyneed_backened_repo.cart.CartService;
import com.example.dailyneed_backened_repo.cart.repository.Cart;
import com.example.dailyneed_backened_repo.cart.view.models.CartRequest;
import com.example.dailyneed_backened_repo.product.ProductService;
import com.example.dailyneed_backened_repo.product.repository.Product;
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
    private final CartService cartService;



    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;

    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody CartRequest cartRequest) {

        cartService.add(cartRequest);
        return new ResponseEntity<>("Cart Item Successfully Added", HttpStatus.OK);

    }

    @GetMapping("/item")
    public ResponseEntity<List<CartResponse>> fetchAll() {

        List<CartResponse> cartResponses = cartService.getCart();

        return new ResponseEntity<>(cartResponses, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity removeCartItem(@PathVariable Long id) {
        cartService.removeCart(id);
        return new ResponseEntity<>("Cart Item Removed Successfully", HttpStatus.OK);

    }


}
