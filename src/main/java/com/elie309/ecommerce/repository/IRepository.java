package com.elie309.ecommerce.repository;

import java.util.List;

public interface IRepository<T> {

    List<T> findAll();
    T findById(Long id);
    T save(T t);
    void update(T t);
    void delete(Long id);

}
