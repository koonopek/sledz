package com.sledz.mobileapp.data.models

import com.sledz.mobileapp.R

data class Product (
    val Id: Long = -1,
    val imageRes: Int = R.drawable.phone,
    val title: String = "None",
    val category:String="None",
    val currentPrice:Double=0.0
)

val defaultListOfProducts = listOf(
    Product(1,R.drawable.phone, "Super telefon1","a",10.5),
    Product(2,R.drawable.phone, "Super telefon2","b",233.0),
    Product(3,R.drawable.phone, "Super telefon3","c",1.22),

)