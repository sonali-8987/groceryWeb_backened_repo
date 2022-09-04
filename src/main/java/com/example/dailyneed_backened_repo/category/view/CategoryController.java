package com.example.dailyneed_backened_repo.category.view;

import com.example.dailyneed_backened_repo.category.CategoryService;
import com.example.dailyneed_backened_repo.category.repository.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public ResponseEntity fetchAll() {
        List<Category> categories = categoryService.getCategories();
        if (categories.isEmpty())
            return new ResponseEntity<>("Category Is Not Available", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
