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
    fun loginUser(user: User): Resource<AuthToken> {
        val response = try {
            mainApi.loginUser(user)
        } catch (e: Exception) {
            return Resource.Error("Login Error")
        }
        return Resource.Success(response)
    }

    fun registerUser(user: User): Resource<Boolean> {
        val response = try {
            mainApi.registerUser(user)
        } catch (e: Exception) {
            return Resource.Error("Register Error")
        }
        return Resource.Success(response)
    }

    fun searchProducts(search: Search): Resource<List<Product>> {
        val response = try {
            mainApi.searchProducts(search)
        } catch (e: Exception) {
            return Resource.Error("Search Error")
        }
        return Resource.Success(response)
    }

    fun subscribeProduct(id: Long, user: User): Resource<Product> {
        val response = try {
            mainApi.subscribeProduct(id, user)
        } catch (e: Exception) {
            return Resource.Error("Search Error")
        }
        return Resource.Success(response)
    }

    fun getSubscribed(user: User): Resource<List<Product>> {
        val response = try {
            mainApi.getSubscribed(user)
        } catch(e: Exception) {
            return Resource.Error("Search Error")
        }
        return Resource.Success(response)
    }

    fun getProductHistory(id: Long): Resource<ProductDetails> {
        val response = try {
            mainApi.getProductHistory(id)
        } catch(e: Exception) {
            return Resource.Error("Search Error")
        }
        return Resource.Success(response)
    }

    fun unsubscribeProduct(id: Long, user: User): Resource<Product> {
        val response = try {
            mainApi.unsubscribeProduct(id, user)
        } catch(e: Exception) {
            return Resource.Error("Search Error")
        }
        return Resource.Success(response)
    }

}
