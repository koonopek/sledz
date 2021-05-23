package com.sledz.mobileapp.data.remote

import com.sledz.mobileapp.data.models.Product
import com.sledz.mobileapp.data.models.ProductDetails
import com.sledz.mobileapp.data.models.Search
import com.sledz.mobileapp.data.models.User
import retrofit2.Call
import retrofit2.http.*

interface MainApi {
    @POST("/user/login")
    fun loginUser(@Body user: User): Call<Boolean>

    @POST("/user/register")
    fun registerUser(@Body user: User): Call<Boolean>

    @GET("products/search")
    fun searchProducts(@Body search: Search): Call<List<Product>>

    @POST("products/subscribe/{productId}")
    fun subscribeProduct(@Path("productId") id:Long, @Body user:User): Call<Product>

    @GET("products/subscribed")
    fun getSubscribed(@Body user:User): Call<List<Product>>

    @GET("products/get/{productID}")
    fun getProductHistory(@Path("productId") id:Long): Call<ProductDetails>

    @DELETE("products/unsubscribe/{productId}")
    fun unsubscribeProduct(@Path("productId") id:Long, @Body user:User): Call<Product>
}