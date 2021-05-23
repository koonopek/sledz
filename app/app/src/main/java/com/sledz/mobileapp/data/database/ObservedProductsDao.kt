package com.sledz.mobileapp.data.database

import androidx.room.*
import com.sledz.mobileapp.data.database.entities.ObservedProduct

// jak ktoś będzie miał ponad 50 produktów, to trzeba to będzie jakoś ładować po kolei

@Dao
interface ObservedProductsDao {
    @Query("SELECT * FROM observedproduct")
    suspend fun getAll(): List<ObservedProduct>

    @Insert
    suspend fun insert(product: ObservedProduct)

    @Update
    suspend fun update(product: ObservedProduct)

    @Delete
    suspend fun delete(product: ObservedProduct)
}