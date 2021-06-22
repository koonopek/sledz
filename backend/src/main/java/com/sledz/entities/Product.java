package com.sledz.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    
    @Column(unique = true)
    public String name;

    @Column()
    public String description;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public List<Value> valueHistory;

    @ManyToOne(fetch = FetchType.EAGER)
    public Category category;

    public Product(String name, String description, List<Value> valueHistory, Category category) {
        this.name = name;
        this.description = description;
        this.valueHistory = valueHistory;
        this.category = category;
    }

    public Product() {}


}
