package com.sledz.mobileapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sledz.mobileapp.data.models.Product
import com.sledz.mobileapp.data.models.ProductDetails
import com.sledz.mobileapp.data.models.Search
import com.sledz.mobileapp.data.models.User
import com.sledz.mobileapp.data.remote.MainApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val mainApi: MainApi
){

    fun loginUser(user: User): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()

        mainApi.loginUser(user).enqueue( object: Callback<Boolean> {

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val resBody = response.body()
                if(response.code() == 200 && resBody != null) {
                    data.value = resBody
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                data.value = null

            }

        })

        return data
    }

    fun registerUser(user:User): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()

        mainApi.registerUser(user).enqueue(object: Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val resBody = response.body()
                if(response.code() == 201 && resBody != null) {
                    data.value = resBody
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                data.value = null
            }

        })

        return data
    }

    fun searchProducts(search: Search): LiveData<List<Product>> {
        val data = MutableLiveData<List<Product>>()

        mainApi.searchProducts(search).enqueue(object: Callback<List<Product>> {

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val resBody = response.body()
                if(response.code() == 200 && resBody != null) {
                    data.value = resBody
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                data.value = null
            }

        })

        return data
    }

    fun subscribeProduct(id:Long, user:User): LiveData<Product> {
        val data = MutableLiveData<Product>()

        mainApi.subscribeProduct(id, user).enqueue(object: Callback<Product> {

            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                val resBody = response.body()
                if(response.code() == 200 && resBody != null) {
                    data.value = resBody
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                data.value = null
            }

        })

        return data
    }

    fun getSubscribed(user:User): LiveData<List<Product>> {
        val data = MutableLiveData<List<Product>>()

        mainApi.getSubscribed(user).enqueue(object: Callback<List<Product>> {

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val resBody = response.body()
                if(response.code() == 200 && resBody != null) {
                    data.value = resBody
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                data.value = null
            }

        })

        return data
    }

    fun getProductHistory(id:Long): LiveData<ProductDetails> {
        val data = MutableLiveData<ProductDetails>()

        mainApi.getProductHistory(id).enqueue(object: Callback<ProductDetails> {

            override fun onResponse(call: Call<ProductDetails>, response: Response<ProductDetails>) {
                val resBody = response.body()
                if(response.code() == 200 && resBody != null) {
                    data.value = resBody
                }
            }

            override fun onFailure(call: Call<ProductDetails>, t: Throwable) {
                data.value = null
            }

        })

        return data
    }

    fun unsubscribeProduct(id:Long, user:User): LiveData<Product> {
        val data = MutableLiveData<Product>()

        mainApi.unsubscribeProduct(id, user).enqueue(object: Callback<Product> {

            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                val resBody = response.body()
                if(response.code() == 200 && resBody != null) {
                    data.value = resBody
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                data.value = null
            }

        })

        return data
    }

}
