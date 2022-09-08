package com.example.dailyneed_backened_repo.cart.view;

import com.example.dailyneed_backened_repo.DailyneedBackenedRepoApplication;
import com.example.dailyneed_backened_repo.cart.repository.Cart;
import com.example.dailyneed_backened_repo.cart.repository.CartRepository;
import com.example.dailyneed_backened_repo.category.repository.Category;
import com.example.dailyneed_backened_repo.category.repository.CategoryRepository;
import com.example.dailyneed_backened_repo.product.repository.Product;
import com.example.dailyneed_backened_repo.product.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = DailyneedBackenedRepoApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@WithMockUser
public class CartControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    Category category;
    Product product;

    Cart cart;

    @BeforeEach
    public void beforeEach() {
        cartRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.deleteAll();


        category = categoryRepository.save(new Category(1L, "VEGETABLES"));

        product = productRepository.save(new Product("Onion", new BigDecimal(20), category));

        cart = cartRepository.save(new Cart(product, 2, 1L));
    }

    @AfterEach
    public void afterEach() {
        cartRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void shouldSaveProductWhenDetailsAreValid() throws Exception {
        final String requestJson = "{" +
                "\"product_id\": \"" + product.getId() + "\"," +
                "\"quantity\": 2," +
                "\"user_id\": 1" +
                "}";

        mockMvc.perform(post("/cart/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(requestJson))
                .andExpect(status().isOk());

        assertThat(cartRepository.findAll().size(), is(2));

    }

    @Test
    void shouldReturnCartDetailsWhenCartIsNotEmpty() throws Exception {
        Long user_id = cart.getUser_id();
        mockMvc.perform(get("/cart/item"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":" + cart.getId() + "," +
                        "\"product\":{\"id\":" + product.getId() + ",\"item\":\"Onion\",\"price\":20.00,\"category\":{\"id\":" + category.getId() + ",\"category\":\"VEGETABLES\"}}," +
                        "\"quantity\":" + cart.getQuantity() + "," +
                        "\"user_id\":" + user_id +
                        "}]"));

    }

    @Test
    void shouldRemoveCartItemWhenItemIdIsValid() throws Exception {

        Product newProduct = productRepository.save(new Product("Potato", new BigDecimal(30), category));

        Cart newCart = cartRepository.save(new Cart(newProduct, 3, 1L));


        Long id = newCart.getId();

        mockMvc.perform(delete("/cart/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("Cart Item Removed Successfully"));
    }


}
