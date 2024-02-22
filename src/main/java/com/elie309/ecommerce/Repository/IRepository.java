package com.elie309.ecommerce.Repository;

import java.util.List;

public interface IRepository<T> {

    List<T> findAll();
    T findById(Long id);
    T save(T t);
    T update(T t);
    void delete(Long id);

}
