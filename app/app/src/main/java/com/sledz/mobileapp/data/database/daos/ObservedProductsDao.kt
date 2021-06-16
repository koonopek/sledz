package com.sledz.mobileapp.data.database.daos

import androidx.room.*
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.data.database.entities.Price

@Dao
interface ObservedProductsDao {

    @Query("SELECT * FROM ObservedProduct WHERE observedProductId = :id")
    suspend fun getById(id: Long): ObservedProduct

    @Query("SELECT * FROM ObservedProduct WHERE name = :name")
    suspend fun getByName(name: String): List<ObservedProduct>

    @Query("SELECT * FROM ObservedProduct WHERE categoryName = :categoryName")
    suspend fun getByCategory(categoryName: String): List<ObservedProduct>

    @Query("SELECT * FROM ObservedProduct")
    suspend fun getAll(): List<ObservedProduct>

    @Insert
    suspend fun insert(product: ObservedProduct)

    @Delete
    suspend fun delete(product: ObservedProduct)
}