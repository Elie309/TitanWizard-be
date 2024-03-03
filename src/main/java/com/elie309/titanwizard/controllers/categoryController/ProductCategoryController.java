package com.elie309.titanwizard.controllers.categoryController;

import com.elie309.titanwizard.models.productsModels.categoriesModels.ProductCategory;
import com.elie309.titanwizard.repository.productsRepository.categoryRepository.ProductCategoryRepository;
import com.elie309.titanwizard.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/product-categories")
public class ProductCategoryController {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryController(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductCategory>> getProductCategoryById(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name
    ) {
        List<ProductCategory> productCategories;

        if (id != null && name == null) {
            productCategories = Collections.singletonList(productCategoryRepository.findById(id));
        }else if (id == null && name != null){
            productCategories = productCategoryRepository.findByTitle(name);
        }else if(id == null){ // Here the name will be null always
            productCategories = productCategoryRepository.findAll();            
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Parameters, only use the name or the id");
        }
        return ResponseEntity.ok(productCategories);

    }


    @PostMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {

        if(productCategory.getProductCategory() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid body");
        }

        productCategoryRepository.save(productCategory);
        return new ResponseEntity<>(productCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Response> updateProductCategory(@PathVariable Long id, @RequestBody ProductCategory productCategory) {

        if(productCategory.getProductCategory() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid body");
        }

        productCategory.setProductCategoryId(id);

        productCategoryRepository.update(productCategory);

        return ResponseEntity.ok(new Response("Record updated", true));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> deleteProductCategory(@PathVariable Long id) {
        productCategoryRepository.delete(id);
        return ResponseEntity.ok(new Response("Record deleted", true));
    }
}