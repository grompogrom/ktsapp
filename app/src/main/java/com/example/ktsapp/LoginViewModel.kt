package com.example.ktsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val mutableLoginState = MutableLiveData(LoginData(email = "", password = ""))

    val state: LiveData<LoginData>
        get() = mutableLoginState

    fun updateLogin(email: String, password: String){
        mutableLoginState.value = LoginData(email, password)
    }

}