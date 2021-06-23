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
import dagger.hilt.android.scopes.ActivityScoped
import java.util.*
import javax.inject.Inject

@SuppressLint("ServiceCast")
@ActivityScoped
class MainRepository @Inject constructor(
    context: Context,
    private val mainApi: MainApi,
) {

    private val store = Store(context)

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

        Log.d("MainRepository","Bearer " + response.token)

        store.write(Constants.API_TOKEN,"Bearer " + response.token)

        return Resource.Success(response)
    }

    suspend fun registerUser(user: User): Resource<Int> {
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

    suspend fun getProductDetails(productId: Long): Resource<ObservedProduct> {
        return Resource.Success(db.getOneProduct(productId))
    }

    suspend fun subscribeProduct(productId: Long): Resource<Long> {

        val response = try {
            mainApi.subscribeProduct(productId, apiToken)
        } catch (e: Exception) {
            return Resource.Error("Could not subscribe to product")
        }

        return Resource.Success(response)
    }

    suspend fun getSubscribed(): Resource<List<ObservedProduct>> {

        try {
            val lastReadTime = store.read(Constants.LAST_FETCHED_SUBSCRIBED).toLong()


            // TODO: replace 0 with Constatnts.
//            if (lastReadTime + 0 >= Date().time || isConnected) {
//                Log.d("MainRepository","Using cached products")
//                return Resource.Success(db.getProducts())
//            }
        } catch (e: Exception) {
            Log.d("MainRepository",e.toString())
        }

        val response = try {
            mainApi.getSubscribed(apiToken)
        } catch (e: Exception) {
            Log.d("MainRepository",e.toString())

            return Resource.Error("Search Error")
        }

        Log.d("MainRepository",response.size.toString())
        db.updateProducts(response)
        store.write(Constants.LAST_FETCHED_SUBSCRIBED, Date().time.toString())

        return Resource.Success(db.getProducts())
    }

    suspend fun unsubscribeProduct(productId: Long): Resource<Unit> {
        val response = try {
            db.removeProduct(db.getOneProduct(productId))
            mainApi.unsubscribeProduct(productId, apiToken)
        } catch (e: Exception) {
            return Resource.Error("Search Error")
        }

        return Resource.Success(response)
    }

}
