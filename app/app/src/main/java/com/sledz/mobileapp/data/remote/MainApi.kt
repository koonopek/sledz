package com.sledz.mobileapp.data.remote

import com.sledz.mobileapp.data.models.*
import retrofit2.http.*

interface MainApi {
    @POST("user/login")
    suspend fun loginUser(@Body user: User): AuthToken

    @POST("user/register")
    suspend fun registerUser(@Body user: User): Int

    @GET("products/search")
    suspend fun searchProducts(@Body search: Search, @Header("Authorization") token: String): List<ProductRemote>

    @POST("products/subscribe/{productId}")
    suspend fun subscribeProduct(@Path("productId") id:Long, @Header("Authorization") token: String): ProductRemote

    @DELETE("products/unsubscribe/{productId}")
    suspend fun unsubscribeProduct(@Path("productId") id:Long, @Header("Authorization") token: String)

    @GET("products/subscribed")
    suspend fun getSubscribed(@Header("Authorization") token: String): List<ProductRemote>

}