package com.example.dailyneed_backened_repo.category;

import com.example.dailyneed_backened_repo.category.repository.Category;
import com.example.dailyneed_backened_repo.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public boolean checkIfCategoryExists(Long id) {
        return categoryRepository.findById(id).isPresent();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }
}
