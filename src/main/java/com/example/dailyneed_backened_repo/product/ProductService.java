package com.example.dailyneed_backened_repo.product;

import com.example.dailyneed_backened_repo.category.CategoryService;
import com.example.dailyneed_backened_repo.category.repository.Category;
import com.example.dailyneed_backened_repo.exceptions.CategoryNotFoundException;
import com.example.dailyneed_backened_repo.exceptions.ItemAlreadyExistException;
import com.example.dailyneed_backened_repo.exceptions.PriceCannotBeNegativeException;
import com.example.dailyneed_backened_repo.exceptions.ProductNotFoundException;
import com.example.dailyneed_backened_repo.product.repository.Product;
import com.example.dailyneed_backened_repo.product.repository.ProductRepository;
import com.example.dailyneed_backened_repo.product.view.models.ProductRequest;
import com.example.dailyneed_backened_repo.product.view.models.ProductUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final CategoryService categoryService;


    public ProductService(ProductRepository productRepository,CategoryService categoryService) {
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

    public void updateProductDetails(ProductUpdateRequest productUpdateRequest) throws ProductNotFoundException, PriceCannotBeNegativeException, CategoryNotFoundException, ItemAlreadyExistException {
        Long id = productUpdateRequest.getId();
        if(!checkIfProductExist(id))
            throw new ProductNotFoundException();

        String item = productUpdateRequest.getItem();
        BigDecimal price = productUpdateRequest.getPrice();
        Long category_id = productUpdateRequest.getCategory_id();

        if(!categoryService.checkIfCategoryExists(category_id))
            throw new CategoryNotFoundException();

        Category category = categoryService.findById(category_id);
        Product product = new Product(id,item,price,category);


        validateUpdatedPrice(product);
        validateUpdatedItem(product);
        saveUpdatedProductDetails(product);
    }

    private boolean checkIfProductExist(Long id) {
        return !(productRepository.findById(id).isEmpty());
    }

    public void validateUpdatedPrice(Product product) throws PriceCannotBeNegativeException {
        if (product.getPrice().intValue() < 0)
            throw new PriceCannotBeNegativeException();
    }

    public void validateUpdatedItem(Product product) throws ItemAlreadyExistException {
        Long id = product.getId();
        String newItem = product.getItem();
        Product existingProduct = productRepository.findById(id).get();
        String existingItem = existingProduct.getItem();

        if (checkIfItemExist(product.getItem()) && !(existingItem.equals(newItem)))
            throw new ItemAlreadyExistException();

    }

    private void saveUpdatedProductDetails(Product product) {
        productRepository.save(product);
    }

    public void removeProduct(Long id) throws ProductNotFoundException {
        if(!checkIfProductExist(id))
            throw new ProductNotFoundException();
        productRepository.deleteById(id);
    }

    public Product findById(Long product_id) {
        return productRepository.findById((product_id)).get();
    }
}

