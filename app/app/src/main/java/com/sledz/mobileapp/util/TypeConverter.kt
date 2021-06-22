package com.sledz.mobileapp.util

import android.graphics.Color
import android.util.Log
import com.klim.tcharts.entities.ChartData
import com.klim.tcharts.entities.ChartItem
import com.sledz.mobileapp.R
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.data.database.entities.Price
import com.sledz.mobileapp.data.models.Product

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
}