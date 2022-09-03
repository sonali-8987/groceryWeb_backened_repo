package com.example.dailyneed_backened_repo.product.view;

import com.example.dailyneed_backened_repo.product.ProductService;
import com.example.dailyneed_backened_repo.product.view.models.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody ProductRequest productRequest) {

        BigDecimal price = productRequest.getPrice().setScale(2, RoundingMode.CEILING);
        String item = productRequest.getItem();
        String category = productRequest.getCategory();

        if (item == "") {
            return new ResponseEntity<>("item cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (category == "") {
            return new ResponseEntity<>("category cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (price.intValue() < 0) {
            return new ResponseEntity<>("price cannot be negative", HttpStatus.BAD_REQUEST);
        }
        productService.add(productRequest.getCategory(), productRequest.getItem(), productRequest.getPrice());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
