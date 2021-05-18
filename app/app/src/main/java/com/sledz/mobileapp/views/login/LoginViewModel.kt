package com.sledz.mobileapp.views.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

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