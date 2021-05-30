package com.sledz.entities;

import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;


    @Column()
    public Double value;

    @Basic
    @Temporal(TemporalType.DATE)
    public Calendar date;
}
