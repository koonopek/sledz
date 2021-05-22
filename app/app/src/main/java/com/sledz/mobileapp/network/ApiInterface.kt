package com.sledz.mobileapp.network

import com.sledz.mobileapp.data.ProductHistoryModel
import com.sledz.mobileapp.data.ProductListElementModel
import com.sledz.mobileapp.data.SearchModel
import com.sledz.mobileapp.data.UserModel
import retrofit2.Call
import retrofit2.http.*

/**
 * All available api interfaces
 */

interface ApiInterface {

    @POST("/user/login")
    fun loginUser(@Body user: UserModel): Call<Boolean>

    @POST("/user/register")
    fun registerUser(@Body user: UserModel): Call<Boolean>

    @GET("products/search")
    fun searchProducts(@Body search:SearchModel): Call<List<ProductListElementModel>>

    @POST("products/subscribe/{productId}")
    fun subscribeProduct(@Path("productId") id:Long, @Body user:UserModel): Call<ProductListElementModel>

    @GET("products/subscribed")
    fun getSubscribed(@Body user:UserModel): Call<List<ProductListElementModel>>

    @GET("products/get/{productID}")
    fun getProductHistory(@Path("productId") id:Long): Call<ProductHistoryModel>

    @DELETE("products/unsubscribe/{productId}")
    fun unsubscribeProduct(@Path("productId") id:Long, @Body user:UserModel): Call<ProductListElementModel>

}