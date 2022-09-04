package com.example.dailyneed_backened_repo.product.view;

import com.example.dailyneed_backened_repo.exceptions.*;
import com.example.dailyneed_backened_repo.product.ProductService;
import com.example.dailyneed_backened_repo.product.repository.Product;
import com.example.dailyneed_backened_repo.product.view.models.ProductRequest;
import com.example.dailyneed_backened_repo.product.view.models.ProductUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody ProductRequest productRequest) {
        try {
            productService.add(productRequest);
            return new ResponseEntity<>("Product Successfully Added", HttpStatus.OK);
        } catch (ItemAlreadyExistException e) {
            return new ResponseEntity<>("Item Already Present", HttpStatus.BAD_REQUEST);
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>("Category Not Found", HttpStatus.BAD_REQUEST);
        } catch (PriceCannotBeNegativeException e) {
            return new ResponseEntity<>("Price Cannot Be Negative", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/product")
    public ResponseEntity fetchAll() {
        List<Product> products = productService.getProducts();
        if (products.isEmpty())
            return new ResponseEntity<>("Products Is Not Available", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping(value = "/edit_product")
    public ResponseEntity editProductDetails(@RequestBody ProductUpdateRequest productUpdateRequest) {

        try {
            productService.updateProductDetails(productUpdateRequest);

            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        }
        catch (PriceCannotBeNegativeException e) {
            return new ResponseEntity<>("Price Cannot Be Negative", HttpStatus.BAD_REQUEST);

        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("Product Not Found",HttpStatus.BAD_REQUEST);
        }
    }

}
