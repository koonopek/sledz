package com.sledz.mobileapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sledz.mobileapp.data.database.entities.ObservedProduct

@Database(entities = arrayOf(ObservedProduct::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun observedProducts(): ObservedProductsDao

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