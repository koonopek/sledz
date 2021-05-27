package com.sledz.mobileapp.data.remote

import com.sledz.mobileapp.data.models.*
import retrofit2.http.*

interface MainApi {
    @POST("user/login")
    suspend fun loginUser(@Body user: User): AuthToken

    @FormUrlEncoded
    @POST("user/register")
    suspend fun registerUser(@Body user: User): Boolean

    @GET("products/search")
    suspend fun searchProducts(@Body search: Search): List<Product>

    @POST("products/subscribe/{productId}")
    suspend fun subscribeProduct(@Path("productId") id:Long, @Body user:User): Product

    @GET("products/subscribed")
    suspend fun getSubscribed(@Body user:User): List<Product>

    @GET("products/get/{productID}")
    suspend fun getProductHistory(@Path("productId") id:Long): ProductDetails

    @DELETE("products/unsubscribe/{productId}")
    suspend fun unsubscribeProduct(@Path("productId") id:Long, @Body user:User): Product
}