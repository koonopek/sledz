package com.sledz.mobileapp.repository

import android.util.Log
import com.sledz.mobileapp.data.models.*
import com.sledz.mobileapp.data.remote.MainApi
import com.sledz.mobileapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MainRepository @Inject constructor(
    private val mainApi: MainApi
) {
    suspend fun loginUser(user: User): Resource<AuthToken> {
        val response = try {
            mainApi.loginUser(user)
        } catch (e: Exception) {
            return Resource.Error("login user error: $e")
        }
        Log.println(Log.DEBUG, "Login", response.toString())
        return Resource.Success(response)
    }

    suspend fun registerUser(user: User): Resource<RegisterResponse> {
        val response = try {
            mainApi.registerUser(user)
        } catch (e: Exception) {
            return Resource.Error("register user error: $e")
        }
        Log.println(Log.DEBUG, "Register", response.toString())
        return Resource.Success(response)
    }

    suspend fun searchProducts(search: Search): Resource<List<Product>> {
        val response = try {
            mainApi.searchProducts(search)
        } catch (e: Exception) {
            return Resource.Error("search result error: $e")
        }
        Log.println(Log.DEBUG, "Search", response.toString())
        return Resource.Success(response)
    }

    suspend fun subscribeProduct(id: Long, token: AuthToken): Resource<Product> {
        val response = try {
            mainApi.subscribeProduct(id, token)
        } catch (e: Exception) {
            return Resource.Error("subscribe error: $e")
        }
        Log.println(Log.DEBUG, "Subscribe", response.toString())
        return Resource.Success(response)
    }

    suspend fun getSubscribed(token: AuthToken): Resource<List<Product>> {
        val response = try {
            mainApi.getSubscribed(token)
        } catch(e: Exception) {
            return Resource.Error("get subscribed error: $e")
        }
        Log.println(Log.DEBUG, "getSubscribed", response.toString())
        return Resource.Success(response)
    }

    suspend fun getProductHistory(id: Long): Resource<ProductDetails> {
        val response = try {
            mainApi.getProductHistory(id)
        } catch(e: Exception) {
            return Resource.Error("get product history error: $e")
        }
        Log.println(Log.DEBUG, "getProductHistory", response.toString())
        return Resource.Success(response)
    }

    suspend fun unsubscribeProduct(id: Long, token: AuthToken): Resource<Product> {
        val response = try {
            mainApi.unsubscribeProduct(id, token)
        } catch(e: Exception) {
            return Resource.Error("unsubscribe error: $e")
        }
        Log.println(Log.DEBUG, "Unsubscribe", response.toString())
        return Resource.Success(response)
    }

    suspend fun getCategories(): Resource<CategoryList> {
        val response = try {
            mainApi.getCategories()
        } catch (e: Exception) {
            return Resource.Error("get categories error: $e")
        }
        Log.println(Log.DEBUG, "getCategories", response.toString())
        return Resource.Success(response)
    }

}
