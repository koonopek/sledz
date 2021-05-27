package com.sledz.mobileapp.views.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sledz.mobileapp.data.models.User
import com.sledz.mobileapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    fun userLogin() {
        viewModelScope.launch(Dispatchers.Default) {
            val results = repository.loginUser(User("sample1", "password1"))
            Log.println(Log.DEBUG, "***", results.data!!.token)
        }
    }
}