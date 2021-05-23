package com.sledz.mobileapp.data.models

import com.sledz.mobileapp.R

data class Product (
    val imageRes: Int,
    val title: String,
    val category:String="None",
    val currentPrice:Float=0.0f
)

val defaultListOfProducts = listOf(
    Product(R.drawable.phone, "Super telefon"),
    Product(R.drawable.phone, "Super telefon"),
    Product(R.drawable.phone, "Super telefon"),
    Product(R.drawable.phone, "Super telefon"),
    Product(R.drawable.phone, "Super telefon"),
    Product(R.drawable.phone, "Super telefon"),
    Product(R.drawable.phone, "Super telefon"),
    Product(R.drawable.phone, "Super telefon"),
    Product(R.drawable.phone, "Super telefon"),
    Product(R.drawable.phone, "Super telefon"),
    Product(R.drawable.phone, "Super telefon"),
)