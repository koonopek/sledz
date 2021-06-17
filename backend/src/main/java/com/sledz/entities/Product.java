package com.sledz.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    
    @Column(unique = true)
    public String name;

    @Column()
    public String description;

    @OneToMany()
    public List<Value> valueHistory;

    @ManyToOne()
    public Category category;

}
