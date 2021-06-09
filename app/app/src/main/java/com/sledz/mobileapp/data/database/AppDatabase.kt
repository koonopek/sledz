package com.sledz.mobileapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sledz.mobileapp.data.database.daos.ListTypeConverter
import com.sledz.mobileapp.data.database.daos.ObservedProductsDao
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.data.database.entities.Price
import com.sledz.mobileapp.data.database.entities.Statistics

@Database(entities = [ObservedProduct::class], version = 1)
@TypeConverters(ListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun observedProductDao(): ObservedProductsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "observed_products_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}