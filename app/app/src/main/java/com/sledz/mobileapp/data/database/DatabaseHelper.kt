package com.sledz.mobileapp.data.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.data.database.entities.Price
import com.sledz.mobileapp.data.models.HistoryValue
import com.sledz.mobileapp.data.models.ProductCategory
import com.sledz.mobileapp.data.models.ProductRemote
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*
import kotlin.collections.List
import kotlin.random.asKotlinRandom

class DatabaseHelper(db : AppDatabase) {

    val db = db

    suspend fun getProducts(): List<ObservedProduct> {
        return db.observedProductDao().getAll()
    }

    suspend fun getOneProduct(productId: Long): ObservedProduct {
        return db.observedProductDao().getById(productId)
    }

    suspend fun updateProducts(products: List<ProductRemote>) {
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

    suspend fun addProduct(product: ProductRemote) {
        val newProduct = ObservedProduct(
            product.id,
            product.name,
            product.description,
            product.category.name,
            product.priceHistory.map { Price(it.value,it.date) },
            listOf(),
            listOf(),
            listOf()
        )

        db.observedProductDao().insert(newProduct)
    }

    suspend fun addDummyData() {
        try {
            val day = 86400000L
            var today = Calendar.getInstance().time.time
            val hist = mutableListOf<HistoryValue>()

            for( i in 1 .. 10) {
                hist.add(HistoryValue(i.toLong(),Random().asKotlinRandom().nextDouble(2.0,300.0), today))
                today += day
            }

            coroutineScope { launch { addProduct(ProductRemote(1,"abc","desc", hist,
                ProductCategory(1,"wtch","Watch")
            ))
                addProduct(ProductRemote(2,"traktor","desc traktor", hist,
                    ProductCategory(2,"ato","Auto")
                ))
                addProduct(ProductRemote(3,"sok","desc sok", hist,
                    ProductCategory(3,"misc","Unk")
                )) } }
        } catch (e:Exception) {
            Log.i("DBHELPER", "already added dummy data $e")
        }


    }
}