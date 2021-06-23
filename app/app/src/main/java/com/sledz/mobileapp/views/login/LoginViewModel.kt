package com.sledz.mobileapp.views.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sledz.mobileapp.data.database.AppDatabase
import com.sledz.mobileapp.data.database.DatabaseHelper
import com.sledz.mobileapp.data.models.AuthToken
import com.sledz.mobileapp.data.models.User
import com.sledz.mobileapp.repository.MainRepository
import com.sledz.mobileapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: MainRepository,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val _loginResponse : MutableLiveData<Resource<AuthToken>> = MutableLiveData()
    val loginResponse: LiveData<Resource<AuthToken>> = _loginResponse

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password


    fun onLoginChange(newString: String) {
        _name.value = newString
    }

    fun onPasswordChange(newString: String) {
        _password.value = newString
    }

    fun userLogin() {
        viewModelScope.launch {
            /* test data */
//            DatabaseHelper(AppDatabase.getInstance(context)).addDummyData()

            _loginResponse.value = repository.loginUser(
                User( _name.value.toString(), _password.value.toString())
            )

        }
    }
}