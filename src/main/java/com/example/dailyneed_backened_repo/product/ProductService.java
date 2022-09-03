package com.example.dailyneed_backened_repo.product;

import com.example.dailyneed_backened_repo.category.CategoryService;
import com.example.dailyneed_backened_repo.exceptions.CategoryNotFoundException;
import com.example.dailyneed_backened_repo.exceptions.ItemAlreadyExistException;
import com.example.dailyneed_backened_repo.exceptions.PriceCannotBeNegativeException;
import com.example.dailyneed_backened_repo.product.repository.Product;
import com.example.dailyneed_backened_repo.product.repository.ProductRepository;
import com.example.dailyneed_backened_repo.product.view.models.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public void add(ProductRequest productRequest) throws ItemAlreadyExistException, CategoryNotFoundException, PriceCannotBeNegativeException {
        Product product = productRequest.getProduct();
        if (checkIfItemExist(product.getItem()))
            throw new ItemAlreadyExistException();
        if (productRequest.getPrice().intValue() < 0)
            throw new PriceCannotBeNegativeException();
        if (!categoryService.checkIfCategoryExists(productRequest.getCategory_id()))
            throw new CategoryNotFoundException();

        product.setCategory(categoryService.findById(productRequest.getCategory_id()));
        productRepository.save(product);
    }

    public boolean checkIfItemExist(String item) {
        return !(productRepository.findByItem(item).isEmpty());
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}

