package com.sledz.mobileapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sledz.mobileapp.data.database.AppDatabase
import com.sledz.mobileapp.data.database.DatabaseHelper
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.data.database.entities.Price
import com.sledz.mobileapp.data.models.HistoryValue
import com.sledz.mobileapp.data.models.ProductCategory
import com.sledz.mobileapp.data.models.ProductRemote
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var db : AppDatabase

    @Before
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java).build()

        databaseHelper = DatabaseHelper(db)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndQuerySingleObservedProduct() {
        val product = ObservedProduct(1,"product1","description1","cat1",
        listOf(Price(10.0, Date().time)), listOf(),listOf(),listOf())

        db.observedProductDao().insert(product)

        val byId = databaseHelper.getOneProduct(1)

        Assert.assertEquals(product,byId)
    }


    @Test
    @Throws(Exception::class)
    fun insertAndQueryObservedProducts() {
        val product1 = ObservedProduct(0,"product0","description0","cat2",
            listOf(Price(11.0, Date().time)), listOf(),listOf(),listOf())
        val product2 = ObservedProduct(1,"product1","description1","cat1",
            listOf(Price(12.0, Date().time)), listOf(),listOf(),listOf())
        val product3 = ObservedProduct(2,"product2","description2","cat1",
            listOf(Price(13.0, Date().time)), listOf(),listOf(),listOf())

        db.observedProductDao().insert(product1)
        db.observedProductDao().insert(product2)
        db.observedProductDao().insert(product3)

        val observedProducts = databaseHelper.getProducts()

         Assert.assertEquals(listOf(product1,product2,product3),observedProducts)
    }

    @Test
    @Throws(Exception::class)
    fun updateProducts() {
        // current database state

        val product1 = ObservedProduct(0,"product0","description0","cat2",
            listOf(Price(11.0, Date().time)), listOf(),listOf(),listOf())
        val product2 = ObservedProduct(1,"product1","description1","cat1",
            listOf(Price(12.0, Date().time)), listOf(),listOf(),listOf())
        val product3 = ObservedProduct(2,"product2","description2","cat1",
            listOf(Price(13.0, Date().time)), listOf(),listOf(),listOf())

        db.observedProductDao().insert(product1)
        db.observedProductDao().insert(product2)
        db.observedProductDao().insert(product3)

        // new state from remote

        val remoteProducts = listOf(
            ProductRemote(0,"name10","description10", listOf(HistoryValue(0,1.0,11),HistoryValue(3,2.0,12)), ProductCategory(1,"qwe","cat4")),
            ProductRemote(1,"name11","description11", listOf(HistoryValue(1,2.0,11),HistoryValue(4,2.0,12)), ProductCategory(1,"qwe","cat4")),
            ProductRemote(2,"name12","description12", listOf(HistoryValue(2,3.0,11),HistoryValue(5,2.0,12)), ProductCategory(1,"qwe","cat4"))
        )

        // update
        databaseHelper.updateProducts(remoteProducts)

        // updated state
        Assert.assertEquals(db.observedProductDao().getById(0),
            ObservedProduct(0,"name10","description10","cat4",
            listOf(Price(1.0, 11),Price(2.0, 12)), listOf(),listOf(),listOf()))

        Assert.assertEquals(db.observedProductDao().getById(1),
            ObservedProduct(1,"name11","description11","cat4",
                listOf(Price(2.0, 11),Price(2.0, 12)), listOf(),listOf(),listOf()))

        Assert.assertEquals(db.observedProductDao().getById(2),
            ObservedProduct(2,"name12","description12","cat4",
                listOf(Price(3.0, 11),Price(2.0, 12)), listOf(),listOf(),listOf()))

        val products = databaseHelper.getProducts()
        Assert.assertEquals(products.size,3)

    }
}