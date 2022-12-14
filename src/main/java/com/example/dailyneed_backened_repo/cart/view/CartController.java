package com.example.dailyneed_backened_repo.cart.view;

import com.example.dailyneed_backened_repo.cart.CartService;
import com.example.dailyneed_backened_repo.cart.view.models.CartRequest;
import com.example.dailyneed_backened_repo.exceptions.ItemAlreadyAddedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody CartRequest cartRequest) throws ItemAlreadyAddedException {

        try {
            cartService.add(cartRequest);
            return new ResponseEntity<>("Cart Item Successfully Added", HttpStatus.OK);
        } catch (ItemAlreadyAddedException e) {
            return new ResponseEntity<>("Item Already Added", HttpStatus.BAD_REQUEST);
        }

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

    @GetMapping(value = "/total_price")
    public ResponseEntity<BigDecimal> totalPrice() {
        BigDecimal totalPrice = cartService.calculateTotalPrice();
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    @DeleteMapping(value = "/reset")
    public ResponseEntity resetCart() {
        cartService.resetCart();
        return new ResponseEntity<>("Cart Reset Successfully", HttpStatus.OK);

    }


}
