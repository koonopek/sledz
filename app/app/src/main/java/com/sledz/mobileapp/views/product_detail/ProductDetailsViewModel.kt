package com.sledz.mobileapp.views.product_detail

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sledz.mobileapp.data.database.AppDatabase
import com.sledz.mobileapp.data.database.DatabaseHelper
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.repository.MainRepository
import com.sledz.mobileapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: MainRepository,
    @ApplicationContext private val context: Context) : ViewModel() {

    val db = AppDatabase.getInstance(context)
    val dbHelper = DatabaseHelper(db)

    private val _productDetails: MutableLiveData<Resource<ObservedProduct>> = MutableLiveData()
    val productDetails: LiveData<Resource<ObservedProduct>> = _productDetails

    fun loadProduct(id: Long) {
        viewModelScope.launch {
            _productDetails.value = Resource.Success(dbHelper.getOneProduct(id))
            Log.i("ProductDetailsVM", "Loaded data id: $id, ${productDetails.value!!.data}")
        }
    }
}