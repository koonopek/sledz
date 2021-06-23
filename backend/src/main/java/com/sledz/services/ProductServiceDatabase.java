package com.sledz.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.sledz.dtos.ProductCategoryDto;
import com.sledz.dtos.ProductDto;
import com.sledz.dtos.ValueDto;
import com.sledz.entities.Category;
import com.sledz.entities.Product;
import com.sledz.entities.Subscription;
import com.sledz.repositories.CategoryRepository;
import com.sledz.repositories.ProductRepository;
import com.sledz.repositories.SubscribtionRepository;
import com.sledz.repositories.UserRepository;

import com.sledz.services.ProductProvider.ProductQuery;
import com.sledz.services.Searcher.MockSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceDatabase extends MockSearcher implements ProductService  {

    @Autowired ProductRepository productRepository;
    @Autowired SubscribtionRepository subscribtionRepository;
    @Autowired UserRepository userRepository;
    @Autowired CategoryRepository categoryRepository;

    @Transactional
    public List<ProductDto> getSubscribedProducts(String username) {
        System.out.println(username);
        var user = this.userRepository.findByName(username).get(0);
        var subscribedProducts = this.subscribtionRepository.findByUser(user);

        return subscribedProducts.stream().map(s -> {
            return ProductServiceDatabase.producToProdcutDto(s.product);
        }).collect(Collectors.toList());
    }

    public ProductDto getProductDetails(Long productId) {
        return ProductServiceDatabase.producToProdcutDto(this.productRepository.findById(productId).get());
    }

    @Override
    @Transactional
    public List<ProductDto> searchProduct(ProductQuery query) {
        var searchResult = super.searchProduct(query);

        addCategories(searchResult);

       productRepository.saveAll(searchResult.stream()
                .filter(s -> !productRepository.existsByName(s.name))
                .map(s -> new Product(s.name,s.description,
                        s.priceHistory.stream().map(v -> v.toEntity()).collect(Collectors.toList()),
                        categoryRepository.findByExternalId(s.category.externalId)))
                .collect(Collectors.toList()));

       var list = productRepository.findByNameContaining(query.phrase);
       Collections.shuffle(list);

       return list.stream().limit(5).map(s -> new ProductDto(s)).collect(Collectors.toList());
    }

    @Transactional
    public Subscription createSubscription(String username, Long productId) {
        var user = this.userRepository.findByName(username).get(0);
        var product = this.productRepository.findById(productId);
        var subscription = new Subscription(user, product.get());
        return this.subscribtionRepository.save(subscription);
    }

    @Transactional
    public void removeSubscription(String username, Long productId) {
        var product = this.productRepository.findById(productId);
        var user = this.userRepository.findByName(username).get(0);

        this.subscribtionRepository.deleteByProductAndUser(product.get(), user);
    }

    public static ProductDto producToProdcutDto(Product product) {
        ProductCategoryDto category = new ProductCategoryDto(product.category.id, product.category.name,
                product.category.externalId);

        return new ProductDto(product.id, product.name, product.description, product.valueHistory.stream().map( s -> new ValueDto(s)).collect(Collectors.toList()), category);
    }

    @Transactional
    private void addCategories(List<ProductDto> prods)
    {
        prods.stream()
                .map(s -> s.category)
                .distinct()
                .filter(c -> !categoryRepository.existsByExternalId(c.externalId))
                .forEach(c -> categoryRepository.save(new Category(c.externalId,c.name)));
    }

}
