package com.example.dailyneed_backened_repo.category.view;

import com.example.dailyneed_backened_repo.DailyneedBackenedRepoApplication;
import com.example.dailyneed_backened_repo.category.repository.Category;
import com.example.dailyneed_backened_repo.category.repository.CategoryRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DailyneedBackenedRepoApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@WithMockUser
public class CategoryControllerIntegrationTest {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;


    @BeforeEach
    public void beforeEach() {

        categoryRepository.deleteAll();
        category = categoryRepository.save(new Category(1L, "VEGETABLES"));
    }

    @AfterEach
    public void afterEach() {
        categoryRepository.deleteAll();
    }


    @Test
    void shouldReturnAllCategories() throws Exception {

        mockMvc.perform(get("/category"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{\"id\":" + category.getId() +
                                ",\"category\":\"VEGETABLES\"}]"));
    }
}
