package com.sledz.mobileapp.views.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sledz.mobileapp.data.models.AuthToken
import com.sledz.mobileapp.data.models.User
import com.sledz.mobileapp.repository.MainRepository
import com.sledz.mobileapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _login = MutableLiveData("")
    val name: LiveData<String> = _login

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password


    fun onLoginChange(newString: String) {
        _login.value = newString
    }

    fun onPasswordChange(newString: String) {
        _password.value = newString
    }
}