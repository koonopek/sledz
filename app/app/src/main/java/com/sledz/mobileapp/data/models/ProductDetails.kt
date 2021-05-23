package com.sledz.mobileapp.data.models

import java.util.*
import kotlin.collections.HashMap

data class ProductDetails(
    val id: Int = 0,
    val name: String = "None",
    val description: String = "Description",
    val priceHistory: List<HashMap<Date, Float>>
)