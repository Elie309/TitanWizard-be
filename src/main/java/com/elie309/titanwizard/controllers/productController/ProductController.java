package com.elie309.titanwizard.controllers.productController;

import com.elie309.titanwizard.models.productsModels.Product;
import com.elie309.titanwizard.repository.productsRepository.ProductRepository;
import com.elie309.titanwizard.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("{id}")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productRepository.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if(product.isNotValid()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product fields are invalid or empty.");
        }
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Response> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setProductId(id);
        if(product.isNotValid()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product fields are invalid or empty.");
        }
        productRepository.update(product);
        return ResponseEntity.ok(new Response("Record updated",true));

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
        productRepository.delete(id);
        return ResponseEntity.ok(new Response("Record Deleted",true));
    }
}