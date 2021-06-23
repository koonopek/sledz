package com.sledz.mobileapp.views.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sledz.mobileapp.data.models.AuthToken
import com.sledz.mobileapp.data.models.User
import com.sledz.mobileapp.repository.MainRepository
import com.sledz.mobileapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val registeredSuccess: Resource<Boolean> = Resource.Loading()

    private val _registerResponse: MutableLiveData<Resource<AuthToken>> = MutableLiveData()
    val registerResponse: LiveData<Resource<AuthToken>> = _registerResponse

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _rpassword = MutableLiveData("")
    val rpassword: LiveData<String> = _rpassword

    private fun samePasswords(): Boolean {
        return _password.value.toString().equals(_rpassword.value.toString())
    }

    fun onEmailChange(newValue: String) {
        _email.value = newValue
    }

    fun onPasswordChange(newValue: String) {
        _password.value = newValue
    }

    fun onRPasswordChange(newValue: String) {
        _rpassword.value = newValue
    }

    fun onRegister() {
        if (samePasswords()) {
            val user = User(_email.value.toString(), _password.value.toString())
            viewModelScope.launch {
                val response = repository.registerUser(user)
                Log.e("AAAAA", response.message.toString())
                when (response) {
                    is Resource.Success -> {
                        _registerResponse.value = repository.loginUser(user)
                    }
                    is Resource.Error -> {
                        _registerResponse.value = Resource.Error("Account already exists")
                    }
                    else -> {
                        _registerResponse.value = Resource.Error("Unknown error")
                    }
                }
            }
        } else {
            _registerResponse.value = Resource.Error("Passwords are different")
        }
    }
}