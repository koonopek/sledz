package com.sledz.mobileapp.data.models

import java.util.*

data class ProductRemote(
    val id: Long,
    val name: String,
    val description: String,
    val priceHistory: List<HistoryValue>,
    val category: ProductCategory
)

data class HistoryValue(
    val id: Long,
    val value: Double,
    val date: Long
)

data class ProductCategory(
    val id: Long,
    val externalId: String,
    val name: String,
)