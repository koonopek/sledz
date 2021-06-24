package com.sledz.mobileapp.util

import android.graphics.Color
import android.util.Log
import com.klim.tcharts.entities.ChartData
import com.klim.tcharts.entities.ChartItem
import com.sledz.mobileapp.R
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.data.database.entities.Price
import com.sledz.mobileapp.data.database.entities.Statistics
import com.sledz.mobileapp.data.models.Product
import com.sledz.mobileapp.data.models.ProductRemote

object TypeConverter {

   public fun ObservedToProduct(observedProduct: ObservedProduct): Product {
        val currentPrice = observedProduct.valueHistory.last().value
        val product = Product(observedProduct.observedProductId, R.drawable.phone, observedProduct.name, observedProduct.categoryName, currentPrice)
        //Log.i("TYPE CONVERTER", "ObservedToProduct() ->$product")
        return product
    }

    public fun ObservedToChartData(observedProduct: ObservedProduct): ChartData {
        val priceHistory = observedProduct.valueHistory
        val items:List<ChartItem> = priceHistory.map {ChartItem(it.date, listOf(it.value.toInt()))}
        val chartData = ChartData(listOf("price"), listOf("price"), listOf(Color.RED), items)
        //Log.i("TYPE CONVERTER", "ObservedToCharData() -> $chartData")
        return chartData
    }

    public fun RemoteToProduct(remote: ProductRemote): Product {
        val currentPrice = remote.priceHistory.last().price
        Log.i("TypeConverter", "RemoteToProduct($remote)")
        val product = Product(Id = remote.id, title = remote.name, category = remote.category.name, currentPrice = currentPrice)
        return product
    }

    public fun RemoteToObserved(remote: ProductRemote): ObservedProduct {
        val priceHist = remote.priceHistory.map { Price(value = it.price,date = it.date) }
        return ObservedProduct(weeklyStats = listOf(Statistics(0.0,0.0,0.0)),
            valueHistory = priceHist,
            observedProductId = remote.id,
            name = remote.name, monthlyStats = listOf(Statistics(0.0,0.0,0.0)),
            globalStats = listOf(Statistics(0.0,0.0,0.0)),
            description = remote.description,
            categoryName = "kategoria")
    }

}