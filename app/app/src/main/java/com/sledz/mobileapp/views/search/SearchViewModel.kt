package com.sledz.mobileapp.views.search

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sledz.mobileapp.data.database.AppDatabase
import com.sledz.mobileapp.data.database.DatabaseHelper
import com.sledz.mobileapp.data.database.entities.ObservedProduct
import com.sledz.mobileapp.data.models.ProductRemote
import com.sledz.mobileapp.data.models.Search
import com.sledz.mobileapp.repository.MainRepository
import com.sledz.mobileapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MainRepository,
    @ApplicationContext context: Context) : ViewModel() {

    val db = AppDatabase.getInstance(context)
    val dbHelper = DatabaseHelper(db)

    private val _search: MutableLiveData<String> = MutableLiveData("")
    val search: LiveData<String> = _search

    private val _searchedProducts: MutableLiveData<Resource<List<ProductRemote>>> = MutableLiveData()
    val searchedProducts: LiveData<Resource<List<ProductRemote>>> = _searchedProducts

    private val _categories: MutableLiveData<List<String>> = MutableLiveData(listOf())
    val categories: LiveData<List<String>> = _categories

    private val _selectedCategory: MutableLiveData<String> = MutableLiveData("")
    val selectedCategory: LiveData<String> = _selectedCategory

    fun loadSearched(phrase: String, category: String) {
        viewModelScope.launch {
            _searchedProducts.value = repository.searchProducts(Search(phrase, category))
            Log.i("SearchVM", "Found products: ${searchedProducts.value?.data}")
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
        } catch (e: Exception) {
            Log.i("SearchVM", "onSelectCategory($index) Error!")
        }

    }

}