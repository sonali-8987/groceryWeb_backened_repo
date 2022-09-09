package com.example.dailyneed_backened_repo.cart;

import com.example.dailyneed_backened_repo.cart.repository.Cart;
import com.example.dailyneed_backened_repo.cart.repository.CartRepository;
import com.example.dailyneed_backened_repo.cart.view.CartResponse;
import com.example.dailyneed_backened_repo.cart.view.models.CartRequest;
import com.example.dailyneed_backened_repo.product.ProductService;
import com.example.dailyneed_backened_repo.product.repository.Product;
import com.example.dailyneed_backened_repo.quantity.Quantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
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
        Quantity quantity;
        if(cartRequest.getUnit().equalsIgnoreCase("KG"))
            quantity = Quantity.createKilogram(cartRequest.getMagnitude());
        else
            quantity = Quantity.createGram(cartRequest.getMagnitude());
        BigDecimal magnitude = quantity.getBaseValue();
        Cart cart = new Cart(product, magnitude, cartRequest.getUser_id());
        cartRepository.save(cart);
    }


    public List<CartResponse> getCart() {

        List<CartResponse> cartResponses = new ArrayList<>();
        List<Cart> carts = cartRepository.findAll();
        for(int i=0;i<carts.size();i++)
        {
            Cart cart = carts.get(i);
            String item = cart.getProduct().getItem();
            BigDecimal price = cart.getProduct().getPrice().multiply( cart.getQuantity());
            cartResponses.add(new CartResponse(cart.getId(),item, cart.getQuantity(),price));
        }
        return cartResponses;
    }

    public void removeCart(Long id) {
        cartRepository.deleteById(id);

    }

    public BigDecimal calculateTotalPrice() {
        List<Cart> carts = cartRepository.findAll();
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal price;

        for(int i=0;i<carts.size();i++)
        {
            Cart cart = carts.get(i);
            price = cart.getProduct().getPrice().multiply( cart.getQuantity());
            totalPrice=totalPrice.add(price);
        }
        return totalPrice;
    }

    public void resetCart() {
        cartRepository.deleteAll();
    }
}
