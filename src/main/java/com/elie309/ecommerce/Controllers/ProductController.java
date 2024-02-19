package com.elie309.ecommerce.Controllers;

import com.elie309.ecommerce.Models.Product;
import com.elie309.ecommerce.Repository.ProductsRepository.ProductRepository;
import com.elie309.ecommerce.Utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
//        Product existingProduct = productRepository.findById(id).getBody();

//        if (existingProduct != null) {

            product.setProductId(id);

            Product updatedProduct = productRepository.update(product).getBody();

            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
        return productRepository.delete(id);
    }
}