package com.sledz.mobileapp.data.database.daos

import androidx.room.*
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.data.database.entities.Price

@Dao
interface ObservedProductsDao {

    @Query("SELECT * FROM ObservedProduct WHERE observedProductId = :id")
    fun getById(id: Long): ObservedProduct

    @Query("SELECT * FROM ObservedProduct WHERE name = :name")
    fun getByName(name: String): List<ObservedProduct>

    @Query("SELECT * FROM ObservedProduct WHERE categoryName = :categoryName")
    fun getByCategory(categoryName: String): List<ObservedProduct>

    @Query("SELECT * FROM ObservedProduct")
    fun getAll(): List<ObservedProduct>

    @Insert
    fun insert(product: ObservedProduct)

    @Delete
    fun delete(product: ObservedProduct)
}