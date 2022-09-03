package com.example.dailyneed_backened_repo.product.view;

import com.example.dailyneed_backened_repo.DailyneedBackenedRepoApplication;
import com.example.dailyneed_backened_repo.product.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @BeforeEach
    public void beforeEach() {
        productRepository.deleteAll();
    }

    @AfterEach
    public void afterEach() {
        productRepository.deleteAll();
    }

    @Test
    void shouldSaveProductDetails() throws Exception {
        final String requestJson = "{" +
                "\"category\": \"Vegetables\"," +
                "\"item\": \"Onion\"," +
                "\"price\": 50" +
                "}";

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(requestJson))
                .andExpect(status().isOk());

        assertThat(productRepository.findAll().size(), is(1));

    }
}
