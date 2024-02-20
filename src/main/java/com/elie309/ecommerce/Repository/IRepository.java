package com.elie309.ecommerce.Repository;

import com.elie309.ecommerce.Utils.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRepository<T> {

    ResponseEntity<List<T>> findAll();
    ResponseEntity<T> findById(Long id);
    ResponseEntity<T> save(T t);
    ResponseEntity<T> update(T t);
    ResponseEntity<Response> delete(Long id);

}
