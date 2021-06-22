package com.sledz.entities;

import com.sledz.dtos.ValueDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Value {

    public Value(double d, long l) {
        this.value =d;
        this.date = l;
    }

    public Value() { }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;


    @Column()
    public Double value;

    @Column()
    public Long date;
}
