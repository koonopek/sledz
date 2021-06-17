package com.sledz.mobileapp.data.database

import android.content.Context
import androidx.room.RoomDatabase
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.data.database.entities.Price
import com.sledz.mobileapp.data.models.ProductRemote
import kotlin.collections.List

class DatabaseHelper(db : AppDatabase) {

    val db = db

    fun getProducts(): List<ObservedProduct> {
        return db.observedProductDao().getAll()
    }

    fun getOneProduct(productId: Long): ObservedProduct {
        return db.observedProductDao().getById(productId)
    }

    fun updateProducts(products: List<ProductRemote>) {
        products.forEach {
            db.observedProductDao().delete(db.observedProductDao().getById(it.id))

            val newProduct = ObservedProduct(
                it.id,
                name = it.name,
                description = it.description,
                categoryName = it.category.name,
                valueHistory = it.priceHistory.map { Price(it.value,it.date) },
                // TO DO: Implement statistic on server side
                weeklyStats = listOf(),
                monthlyStats = listOf(),
                globalStats = listOf()
            )

            db.observedProductDao().insert(newProduct)
        }
    }
}