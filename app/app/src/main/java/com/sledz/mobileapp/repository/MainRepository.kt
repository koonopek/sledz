package com.sledz.mobileapp.repository

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.sledz.mobileapp.data.Store
import com.sledz.mobileapp.data.database.AppDatabase
import com.sledz.mobileapp.data.database.DatabaseHelper
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.data.models.*
import com.sledz.mobileapp.data.remote.MainApi
import com.sledz.mobileapp.util.Constants
import com.sledz.mobileapp.util.Resource
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import java.util.*
import javax.inject.Inject

@SuppressLint("ServiceCast")
@ActivityScoped
class MainRepository @Inject constructor(
    private val mainApi: MainApi,
    private val store: Store,
    @ApplicationContext private val context: Context
) {

    private val isConnected by lazy {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.activeNetworkInfo?.isConnectedOrConnecting == true
    }

    private val db by lazy {
        val db = AppDatabase.getInstance(context)
        DatabaseHelper(db)
    }

    private val apiToken by lazy { store.read(Constants.API_TOKEN) }

    suspend fun loginUser(user: User): Resource<AuthToken> {
        val response = try {
            mainApi.loginUser(user)
        } catch (e: Exception) {
            return Resource.Error("Failed to log in ")
        }

        return Resource.Success(response)
    }

    suspend fun registerUser(user: User): Resource<Boolean> {
        val response = try {
            mainApi.registerUser(user)
        } catch (e: Exception) {
            return Resource.Error("Register Error")
        }
        return Resource.Success(response)
    }

    //
//    suspend fun searchProducts(search: Search): List<ProductRemote> {
//        val response = try {
//            mainApi.searchProducts(search)
//        } catch (e: Exception) {
//            return Resource.Error("Search Error")
//        }
//        return Resource.Success(response)
//    }
//

    suspend fun subscribeProduct(productId: Long): Resource<ProductRemote> {

        val response = try {
            mainApi.subscribeProduct(productId, apiToken)
        } catch (e: Exception) {
            return Resource.Error("Could not subscribe to product")
        }

        return Resource.Success(response)
    }

    suspend fun getSubscribed(): Resource<List<ObservedProduct>> {

        val lastReadTime = store.read(Constants.LAST_FETCHED_SUBSCRIBED).toLong()

        if (lastReadTime + Constants.DAY_IN_MILI_S >= Date().time || isConnected) {
            return Resource.Success(db.getProducts())
        }

        val response = try {
            mainApi.getSubscribed(apiToken)
        } catch (e: Exception) {
            return Resource.Error("Search Error")
        }

        db.updateProducts(response)

        return Resource.Success(db.getProducts())
    }

    suspend fun unsubscribeProduct(productId: Long): Resource<Unit> {
        val response = try {
            mainApi.unsubscribeProduct(productId, apiToken)
        } catch (e: Exception) {
            return Resource.Error("Search Error")
        }

        return Resource.Success(response)
    }

}
