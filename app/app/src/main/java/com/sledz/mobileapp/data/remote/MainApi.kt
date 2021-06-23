package com.sledz.mobileapp.data.remote

import com.sledz.mobileapp.data.models.*
import retrofit2.http.*

interface MainApi {
    @POST("user/login")
    suspend fun loginUser(@Body user: User): AuthToken

    @POST("user/register")
    suspend fun registerUser(@Body user: User): Int

    @POST("products/search")
    suspend fun searchProducts(@Header("Authorization") token: String, @Body search: Search): List<ProductRemote>

    @POST("product/subscription/{productId}")
    suspend fun subscribeProduct(@Path("productId") id:Long, @Header("Authorization") token: String): Long

    @DELETE("product/subscription/{productId}")
    suspend fun unsubscribeProduct(@Path("productId") id:Long, @Header("Authorization") token: String)

    @GET("products/subscribed")
    suspend fun getSubscribed(@Header("Authorization") token: String): List<ProductRemote>

}