package com.sledz.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;

@Entity
@Getter
public class Subscription {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne()
    public User user;

    @OneToOne()
    public Product product;


    public Subscription(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public Subscription() { }
}
