package com.example.dailyneed_backened_repo.bill.view;

import com.example.dailyneed_backened_repo.DailyneedBackenedRepoApplication;
import com.example.dailyneed_backened_repo.bill.repository.Bill;
import com.example.dailyneed_backened_repo.bill.repository.BillRepository;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = DailyneedBackenedRepoApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@WithMockUser
public class BillControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;
    private Product product;

    private Cart cart;


    @BeforeEach
    public void beforeEach() {

        billRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.deleteAll();


        category = categoryRepository.save(new Category(1L, "VEGETABLES"));

        product = productRepository.save(new Product("Onion", new BigDecimal(20), category));

        cart = cartRepository.save(new Cart(product, 2, 1L));

    }

    @AfterEach
    public void afterEach() {

        billRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.deleteAll();

    }

    @Test
    void shouldReturnBillDetailsWhenCartIsNotEmpty() throws Exception {

        mockMvc.perform(get("/bill"))
                .andExpect(status().isOk())
                .andExpect(content().json("[" +
                        "{" +
                        "\"id\":" + cart.getId() + "," +
                        "\"cart_id\":" + cart.getId() + "," +
                        "\"price\":40.00" +
                        "}" +
                        "]"));

    }

}
