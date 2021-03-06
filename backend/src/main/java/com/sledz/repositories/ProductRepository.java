package com.sledz.repositories;

import com.sledz.entities.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    boolean existsByName(String name);
    List<Product> findByNameContaining(String infix);
}
