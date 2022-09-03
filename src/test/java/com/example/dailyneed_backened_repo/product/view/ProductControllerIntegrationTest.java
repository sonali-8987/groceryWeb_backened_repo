package com.example.dailyneed_backened_repo.product.view;

import com.example.dailyneed_backened_repo.DailyneedBackenedRepoApplication;
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
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = DailyneedBackenedRepoApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@WithMockUser
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    Category category;
    Product product;

    @BeforeEach
    public void beforeEach() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();

        category = categoryRepository.save(new Category(1L, "VEGETABLES"));
        product = productRepository.save(new Product("Onion", new BigDecimal(20), category));
    }

    @AfterEach
    public void afterEach() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void shouldSaveProductWhenDetailsAreValid() throws Exception {
        final String requestJson = "{" +
                "\"item\": \"Tomato\"," +
                "\"price\": 50," +
                "\"category_id\": " + category.getId() +
                "}";

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(requestJson))
                .andExpect(status().isOk());

        assertThat(productRepository.findAll().size(), is(2));

    }

    @Test
    void shouldNotSaveProductWhenItemAlreadyPresent() throws Exception {

        final String requestJson = "{" +
                "\"item\": \"Onion\"," +
                "\"price\": 50," +
                "\"category_id\": " + category.getId() +
                "}";

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(requestJson))
                .andExpect(status().isBadRequest());

        assertThat(productRepository.findAll().size(), is(1));

    }

    @Test
    void shouldNotSaveProductWhenPriceIsInvalid() throws Exception {

        final String requestJson = "{" +
                "\"item\": \"Onion\"," +
                "\"price\": -50," +
                "\"category_id\": " + category.getId() +
                "}";

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(requestJson))
                .andExpect(status().isBadRequest());

        assertThat(productRepository.findAll().size(), is(1));

    }

    @Test
    void shouldNotSaveProductWhenCategoryIsNotPresent() throws Exception {

        final String requestJson = "{" +
                "\"item\": \"Onion\"," +
                "\"price\": -50," +
                "\"category_id\":  1" +
                "}";

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(requestJson))
                .andExpect(status().isBadRequest());

        assertThat(productRepository.findAll().size(), is(1));

    }

    @Test
    void shouldReturnProductDetailsWhenProductIsPresent() throws Exception {

        List<Product> products = new ArrayList<>();
        products.add(product);

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":" + product.getId() + ",\"item\":\"Onion\",\"price\":20.00,\"category\":{\"id\":" + category.getId() + ",\"category\":\"VEGETABLES\"}}]"));
    }

    @Test
    void shouldReturnBadRequestWhenNoProductsArePresent() throws Exception {
        productRepository.deleteAll();

        mockMvc.perform(get("/product"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Products Is Not Available"));
    }

}