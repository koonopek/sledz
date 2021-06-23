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
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val db = AppDatabase.getInstance(context)
    val dbHelper = DatabaseHelper(db)

    private val _search: MutableLiveData<String> = MutableLiveData("")
    val search: LiveData<String> = _search

    private val _subscribedProducts: MutableLiveData<Resource<List<ObservedProduct>>> = MutableLiveData()
    val subscribedProducts: LiveData<Resource<List<ObservedProduct>>> = _subscribedProducts

    private val _categories: MutableLiveData<List<String>> = MutableLiveData(listOf())
    val categories: LiveData<List<String>> = _categories

    private val _selectedCategory: MutableLiveData<String> = MutableLiveData("Elektronika")
    val selectedCategory: LiveData<String> = _selectedCategory

    fun loadSubscribed() {
        viewModelScope.launch {
            _subscribedProducts.value = repository.getSubscribed()
            Log.i("MainVM", "products ${subscribedProducts.value!!.data}")
        }
    }

    fun onSearchUpdate(searchUpdate: String) {
        _search.value = searchUpdate
    }

    fun loadCategories() {
        _categories.value = listOf("Elektronika", "Dom i rodzina", "Samochody", "Sport")
    }

    fun onSelectCategory(index: Int) {
        try {
            _selectedCategory.value = _categories.value!!.get(index)
        } catch (e:Exception) {
            Log.i("MainVM", "onSelectCategory($index) Error!")
        }

    }

}