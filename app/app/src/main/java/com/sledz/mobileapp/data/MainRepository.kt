package com.sledz.mobileapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sledz.mobileapp.network.ApiClient
import com.sledz.mobileapp.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {
    private var apiInterface:ApiInterface?=null

    init {
        apiInterface = ApiClient.getApiClient()?.create(ApiInterface::class.java)
    }

    fun loginUser(user:UserModel): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()

        apiInterface?.loginUser(user)?.enqueue( object: Callback<Boolean> {

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

    fun registerUser(user:UserModel): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()

        apiInterface?.registerUser(user)?.enqueue(object: Callback<Boolean> {
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

    fun searchProducts(search: SearchModel): LiveData<List<ProductListElementModel>> {
        val data = MutableLiveData<List<ProductListElementModel>>()

        apiInterface?.searchProducts(search)?.enqueue(object: Callback<List<ProductListElementModel>> {

            override fun onResponse(call: Call<List<ProductListElementModel>>, response: Response<List<ProductListElementModel>>) {
                val resBody = response.body()
                if(response.code() == 200 && resBody != null) {
                    data.value = resBody
                }
            }

            override fun onFailure(call: Call<List<ProductListElementModel>>, t: Throwable) {
                data.value = null
            }

        })

        return data
    }

    fun subscribeProduct(id:Long, user:UserModel): LiveData<ProductListElementModel> {
        val data = MutableLiveData<ProductListElementModel>()

        apiInterface?.subscribeProduct(id, user)?.enqueue(object: Callback<ProductListElementModel> {

            override fun onResponse(call: Call<ProductListElementModel>, response: Response<ProductListElementModel>) {
                val resBody = response.body()
                if(response.code() == 200 && resBody != null) {
                    data.value = resBody
                }
            }

            override fun onFailure(call: Call<ProductListElementModel>, t: Throwable) {
                data.value = null
            }

        })

        return data
    }

    fun getSubscribed(user:UserModel): LiveData<List<ProductListElementModel>> {
        val data = MutableLiveData<List<ProductListElementModel>>()

        apiInterface?.getSubscribed(user)?.enqueue(object: Callback<List<ProductListElementModel>> {

            override fun onResponse(call: Call<List<ProductListElementModel>>, response: Response<List<ProductListElementModel>>) {
                val resBody = response.body()
                if(response.code() == 200 && resBody != null) {
                    data.value = resBody
                }
            }

            override fun onFailure(call: Call<List<ProductListElementModel>>, t: Throwable) {
                data.value = null
            }

        })

        return data
    }

    fun getProductHistory(id:Long): LiveData<ProductHistoryModel> {
        val data = MutableLiveData<ProductHistoryModel>()

        apiInterface?.getProductHistory(id)?.enqueue(object: Callback<ProductHistoryModel> {

            override fun onResponse(call: Call<ProductHistoryModel>, response: Response<ProductHistoryModel>) {
                val resBody = response.body()
                if(response.code() == 200 && resBody != null) {
                    data.value = resBody
                }
            }

            override fun onFailure(call: Call<ProductHistoryModel>, t: Throwable) {
                data.value = null
            }

        })

        return data
    }

    fun unsubscribeProduct(id:Long, user:UserModel): LiveData<ProductListElementModel> {
        val data = MutableLiveData<ProductListElementModel>()

        apiInterface?.unsubscribeProduct(id, user)?.enqueue(object: Callback<ProductListElementModel> {

            override fun onResponse(call: Call<ProductListElementModel>, response: Response<ProductListElementModel>) {
                val resBody = response.body()
                if(response.code() == 200 && resBody != null) {
                    data.value = resBody
                }
            }

            override fun onFailure(call: Call<ProductListElementModel>, t: Throwable) {
                data.value = null
            }

        })

        return data
    }

}