package com.sledz.repositories;

import com.sledz.entities.Category;
import com.sledz.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    public boolean existsByExternalId(String externalId);
    public Category findByExternalId(String externalId);
}
