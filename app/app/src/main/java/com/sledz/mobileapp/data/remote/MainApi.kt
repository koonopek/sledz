package com.sledz.mobileapp.data.remote

import com.sledz.mobileapp.data.models.*
import retrofit2.http.*

interface MainApi {
    @POST("user/login")
    suspend fun loginUser(@Body user: User): AuthToken

    @POST("user/register")
    suspend fun registerUser(@Body user: User): RegisterResponse

    @GET("product/search")
    suspend fun searchProducts(@Body search: Search): List<Product>

    @POST("product/subscribe/{productId}")
    suspend fun subscribeProduct(@Path("productId") id:Long, @Body token:AuthToken): Product

    @GET("product/subscribed")
    suspend fun getSubscribed(@Body token:AuthToken): List<Product>

    @GET("product/{productID}")
    suspend fun getProductHistory(@Path("productId") id:Long): ProductDetails

    @GET("categories")
    suspend fun getCategories(): CategoryList

    @DELETE("product/unsubscribe/{productId}")
    suspend fun unsubscribeProduct(@Path("productId") id:Long, @Body token:AuthToken): Product
}