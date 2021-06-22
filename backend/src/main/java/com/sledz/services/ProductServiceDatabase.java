package com.sledz.services;

import java.util.List;
import java.util.stream.Collectors;

import com.sledz.dtos.ProductCategoryDto;
import com.sledz.dtos.ProductDto;
import com.sledz.entities.Product;
import com.sledz.entities.Subscription;
import com.sledz.repositories.ProductRepository;
import com.sledz.repositories.SubscribtionRepository;
import com.sledz.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceDatabase implements ProductService {

    @Autowired ProductRepository productRepository;
    @Autowired SubscribtionRepository subscribtionRepository;
    @Autowired UserRepository userRepository;


    @Transactional
    public List<ProductDto> getSubscribedProducts(Long userId) {
        var user = this.userRepository.findById(userId);
        var subscribedProducts = this.subscribtionRepository.findByUser(user.get());

        return subscribedProducts.stream().map(s -> {
            return ProductServiceDatabase.producToProdcutDto(s.product);
        }).collect(Collectors.toList());
    }

    public ProductDto getProductDetails(Long productId) {
        return ProductServiceDatabase.producToProdcutDto(this.productRepository.findById(productId).get());
    }

    @Transactional
    public Subscription createSubscription(Long userId, Long productId) {
        var user = this.userRepository.findById(userId);
        var product = this.productRepository.findById(productId);
        var subscription = new Subscription(user.get(), product.get());
        return this.subscribtionRepository.save(subscription);
    }

    @Transactional
    public void removeSubscription(Long userId, Long productId) {
        var product = this.productRepository.findById(productId);
        var user = this.userRepository.findById(userId);

        this.subscribtionRepository.deleteByProductAndUser(product.get(), user.get());
    }

    public static ProductDto producToProdcutDto(Product product) {
        ProductCategoryDto category = new ProductCategoryDto(product.category.id, product.category.name,
                product.category.externalId);

        return new ProductDto(product.id, product.name, product.description, product.valueHistory, category);
    }

}
