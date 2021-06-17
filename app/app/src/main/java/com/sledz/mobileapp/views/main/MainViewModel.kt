package com.sledz.mobileapp.views.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.TypeConverter
import com.sledz.mobileapp.data.database.AppDatabase
import com.sledz.mobileapp.data.database.DatabaseHelper
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.data.database.entities.Price
import com.sledz.mobileapp.data.models.*
import com.sledz.mobileapp.repository.MainRepository
import com.sledz.mobileapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val db = AppDatabase.getInstance(context)
    val dbHelper = DatabaseHelper(db)

    private val _subscribedProducts: MutableLiveData<Resource<List<ObservedProduct>>> = MutableLiveData()
    val subscribedProducts: LiveData<Resource<List<ObservedProduct>>> = _subscribedProducts

    fun loadSubscribed() {
        viewModelScope.launch {
            _subscribedProducts.value = Resource.Success(dbHelper.getProducts())
            Log.i("MainVM", "products ${subscribedProducts.value?.data}")
        }
    }

}