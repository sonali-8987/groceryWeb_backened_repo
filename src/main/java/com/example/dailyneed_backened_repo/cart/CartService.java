package com.example.dailyneed_backened_repo.cart;

import com.example.dailyneed_backened_repo.cart.repository.Cart;
import com.example.dailyneed_backened_repo.cart.repository.CartRepository;
import com.example.dailyneed_backened_repo.cart.view.models.CartRequest;
import com.example.dailyneed_backened_repo.product.ProductService;
import com.example.dailyneed_backened_repo.product.repository.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final ProductService productService;


    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public void add(CartRequest cartRequest) {
        Product product = productService.findById(cartRequest.getProduct_id());
        Cart cart = new Cart(product, cartRequest.getQuantity(), cartRequest.getUser_id());
        cartRepository.save(cart);
    }

    public List<Cart> getCart() {
        return cartRepository.findAll();
    }
}
