package com.elie309.titanwizard.controllers.categoryController;

import com.elie309.titanwizard.models.productsModels.categoriesModels.ProductSubcategory;
import com.elie309.titanwizard.repository.productsRepository.categoryRepository.ProductSubcategoryRepository;
import com.elie309.titanwizard.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product-subcategories")
public class ProductSubcategoryController {

    private ProductSubcategoryRepository productSubcategoryRepository;

    @GetMapping
    public  ResponseEntity<List<ProductSubcategory>> getAllProductSubcategories() {
        List<ProductSubcategory> lists = productSubcategoryRepository.findAll();
        return ResponseEntity.ok(lists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductSubcategory> getProductSubcategoryById(@PathVariable Long id) {
        ProductSubcategory productSubcategory = productSubcategoryRepository.findById(id);
        return ResponseEntity.ok(productSubcategory);
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<ProductSubcategory>> getProductSubcategoriesByCategoryId(@PathVariable Long categoryId) {
         List<ProductSubcategory> lists = productSubcategoryRepository.findByCategoryId(categoryId);
         return ResponseEntity.ok(lists);
    }

    @PostMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<ProductSubcategory> createProductSubcategory(@RequestBody ProductSubcategory productSubcategory) {
         productSubcategoryRepository.save(productSubcategory);
         return new ResponseEntity<>(productSubcategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Response> updateProductSubcategory(@PathVariable Long id, @RequestBody ProductSubcategory productSubcategory) {
        productSubcategory.setProductSubcategoryId(id);
        productSubcategoryRepository.update(productSubcategory);
        return ResponseEntity.ok(new Response("Record updated",true));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> deleteProductSubcategory(@PathVariable Long id) {
        productSubcategoryRepository.delete(id);
        return ResponseEntity.ok(new Response("Record updated",true));
    }
}
