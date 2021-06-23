package com.sledz.mobileapp.data.models

data class ProductRemote(
    val id: Long,
    val name: String,
    val description: String,
    val priceHistory: List<HistoryValue>,
    val category: ProductCategory
)

data class HistoryValue(
    val id: Long,
    val price: Double,
    val date: Long
)

data class ProductCategory(
    val id: Long,
    val externalId: String,
    val name: String,
)


