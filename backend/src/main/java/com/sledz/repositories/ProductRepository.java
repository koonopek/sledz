package com.sledz.repositories;

import com.sledz.entities.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> { }
