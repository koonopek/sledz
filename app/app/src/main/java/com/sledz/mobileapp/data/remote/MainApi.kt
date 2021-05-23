package com.sledz.mobileapp.data.remote

import com.sledz.mobileapp.data.models.*
import retrofit2.http.*

interface MainApi {
    @POST("user/login")
    fun loginUser(@Body user: User): AuthToken

    @POST("user/register")
    fun registerUser(@Body user: User): Boolean

    @GET("products/search")
    fun searchProducts(@Body search: Search): List<Product>

    @POST("products/subscribe/{productId}")
    fun subscribeProduct(@Path("productId") id:Long, @Body user:User): Product

    @GET("products/subscribed")
    fun getSubscribed(@Body user:User): List<Product>

    @GET("products/get/{productID}")
    fun getProductHistory(@Path("productId") id:Long): ProductDetails

    @DELETE("products/unsubscribe/{productId}")
    fun unsubscribeProduct(@Path("productId") id:Long, @Body user:User): Product
}