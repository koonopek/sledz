package com.sledz.mobileapp.data.models

import com.sledz.mobileapp.R

data class Product (
    val imageRes: Int,
    val title: String,
    val category:String,
    val currentPrice:Float
)

val defaultListOfProducts = listOf(
    Product(R.drawable.phone, "Super telefon", "None", 0.0f),
    Product(R.drawable.phone, "Super telefon", "None", 0.0f),
    Product(R.drawable.phone, "Super telefon", "None", 0.0f),
    Product(R.drawable.phone, "Super telefon", "None", 0.0f),
    Product(R.drawable.phone, "Super telefon", "None", 0.0f),
    Product(R.drawable.phone, "Super telefon", "None", 0.0f),
    Product(R.drawable.phone, "Super telefon", "None", 0.0f),
    Product(R.drawable.phone, "Super telefon", "None", 0.0f),
    Product(R.drawable.phone, "Super telefon", "None", 0.0f),
    Product(R.drawable.phone, "Super telefon", "None", 0.0f),
    Product(R.drawable.phone, "Super telefon", "None", 0.0f),
)