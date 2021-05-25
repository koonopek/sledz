package com.sledz.mobileapp.data.models

import java.util.*
import kotlin.collections.HashMap

data class ProductDetails(
    val id: Int,
    val name: String,
    val description: String,
    val priceHistory: List<Pair<Calendar, Float>>
)