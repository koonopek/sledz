package com.sledz.repositories;

import java.util.List;

import com.sledz.entities.Product;
import com.sledz.entities.Subscription;
import com.sledz.entities.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribtionRepository extends CrudRepository<Subscription, Long> {
    List<Subscription> findByUser(User user);

    void deleteByProductAndUser(Product product, User user);
}
