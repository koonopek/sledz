package com.sledz.mobileapp.data

/**
 * Entity class for product displayed in search list and subscription list
 */
data class ProductListElementModel(
    val id:Long=0,
    val name:String="None",
    val category:String="None",
    val currentPrice:Float=0.0f
)
