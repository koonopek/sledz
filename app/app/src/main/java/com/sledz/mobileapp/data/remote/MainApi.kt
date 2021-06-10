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
    suspend fun searchProducts(@Body search: Search): List<ProductRemote>

    @POST("products/subscribe/{productId}")
    suspend fun subscribeProduct(@Path("productId") id:Long): ProductRemote

    @GET("products/subscribed")
    suspend fun getSubscribed(): List<ProductRemote>

    @DELETE("products/unsubscribe/{productId}")
    suspend fun unsubscribeProduct(@Path("productId") id:Long)
}