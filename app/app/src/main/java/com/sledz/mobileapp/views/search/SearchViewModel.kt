package com.sledz.mobileapp.views.search

import androidx.lifecycle.ViewModel
import com.sledz.mobileapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(repository: MainRepository) : ViewModel() {

}