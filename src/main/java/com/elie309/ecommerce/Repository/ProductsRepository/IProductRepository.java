package com.elie309.ecommerce.Repository.ProductsRepository;

import com.elie309.ecommerce.Models.Product;
import com.elie309.ecommerce.Utils.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductRepository {

    ResponseEntity<List<Product>> findAll();
    ResponseEntity<Product> findById(Long productId);
    ResponseEntity<Product> save(Product product);
    ResponseEntity<Product> update(Product product);
    ResponseEntity<Response> delete(Long productId);

}
