package com.sledz.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.sledz.entities.Category;
import com.sledz.entities.Product;
import com.sledz.entities.User;
import com.sledz.entities.Value;
import com.sledz.repositories.CategoryRepository;
import com.sledz.repositories.ProductRepository;
import com.sledz.repositories.SubscribtionRepository;
import com.sledz.repositories.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceDatabaseTest {


    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private SubscribtionRepository subscribtionRepository;
    @Autowired private ProductServiceDatabase productServiceDatabase;
    @Autowired private CategoryRepository categoryRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(productServiceDatabase).isNotNull();
    }

    @Test
    void testCreateSubscription() {
        User user1 = new User("user1","password1");
        var user1Saved = userRepository.save(user1);

        var values = List.of(new Value(1.0,0));
        Product product1 = new Product("produkt1","a", values, categoryRepository.save(new Category()));
        var product1Saved = productRepository.save(product1);

        var id = this.productServiceDatabase.createSubscription("user1", product1Saved.getId()).getId();

        var createdSubscription = subscribtionRepository.findById(id).get();
        
        assertThat(createdSubscription.product.id).isEqualTo(product1Saved.id);
        assertThat(createdSubscription.user.getId()).isEqualTo(user1Saved.getId());
    }

    @Test
    void testGetProductDetails() {
        var values = List.of(new Value(1.0,0));
        Product product1 = new Product("produkt2","a", values, categoryRepository.save(new Category()));
        var product1Saved = productRepository.save(product1);

        var actual = this.productServiceDatabase.getProductDetails(product1.getId());

        assertThat(actual.id).isEqualTo(product1Saved.getId());
    }

    @Test
    void testGetSubscribedProducts() {
        User user2 = new User("user2","password2");
        var user2Saved = userRepository.save(user2);

        for(int i=0; i < 10 ; i++) {
            var values = List.of(new Value(1.0,0));
            var productId = (Long) productRepository.save(new Product("produkta" + i,"a"+i, values, categoryRepository.save(new Category()))).getId();
            this.productServiceDatabase.createSubscription(user2.getName(),productId);
        }
 
        var subscribedProducts = this.productServiceDatabase.getSubscribedProducts("user2");

        assertThat(subscribedProducts.size()).isEqualTo(10);
    }


    @Test
    void testRemoveSubscription() {
        User user3 = new User("user3","password3");
        userRepository.save(user3);

        long productId = 1;
        for(int i=11; i < 20 ; i++) {
            var values = List.of(new Value(1.0,0));
            productId = productRepository.save(new Product("produktb" + i,"a"+i, values, categoryRepository.save(new Category()))).getId();
            this.productServiceDatabase.createSubscription(user3.getName(),productId);
        }

        var subscribedProductsPre = this.productServiceDatabase.getSubscribedProducts("user3");

        assertThat(subscribedProductsPre.size()).isEqualTo(9);

        this.productServiceDatabase.removeSubscription("user3", productId);

        var subscribedProductsPost = this.productServiceDatabase.getSubscribedProducts("user3");

        assertThat(subscribedProductsPost.size()).isEqualTo(8);
    }
}
