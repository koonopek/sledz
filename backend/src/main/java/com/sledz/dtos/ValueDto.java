package com.sledz.dtos;

import com.sledz.entities.Value;

public class ValueDto {
    public long id;
    public long date;
    public double price;

    public Value toEntity(){
        return new Value(price,date);
    }
    public ValueDto(Value v) {
        id = v.id;
        date = v.date;
        price = v.value;
    }

    public ValueDto() {}

}
