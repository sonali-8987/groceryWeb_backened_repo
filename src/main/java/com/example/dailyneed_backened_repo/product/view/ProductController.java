package com.example.dailyneed_backened_repo.product.view;

import com.example.dailyneed_backened_repo.category.repository.CategoryRepository;
import com.example.dailyneed_backened_repo.exceptions.CategoryNotFoundException;
import com.example.dailyneed_backened_repo.exceptions.ItemAlreadyExistException;
import com.example.dailyneed_backened_repo.exceptions.PriceCannotBeNegativeException;
import com.example.dailyneed_backened_repo.product.ProductService;
import com.example.dailyneed_backened_repo.product.view.models.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody ProductRequest productRequest) {
        try {
            productService.add(productRequest);
        } catch (ItemAlreadyExistException e) {
            return new ResponseEntity<>("Item Already Present", HttpStatus.BAD_REQUEST);
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>("Category Not Found", HttpStatus.BAD_REQUEST);
        } catch (PriceCannotBeNegativeException e) {
            return new ResponseEntity<>("Price Cannot Be Negative", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Product Successfully Added", HttpStatus.OK);
    }

}
