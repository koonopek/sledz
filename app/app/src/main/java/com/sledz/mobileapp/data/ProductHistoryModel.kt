package com.sledz.mobileapp.data

import java.util.*
import kotlin.collections.HashMap

/**
 * Entity class used to show price history of a product
 */
data class ProductHistoryModel(
    val id:Int=0,
    val name:String="None",
    val description:String="Description",
    val priceHistory:List<HashMap<Date,Float>>

)
