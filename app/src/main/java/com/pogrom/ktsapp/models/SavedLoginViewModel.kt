package com.pogrom.ktsapp.models

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.pogrom.ktsapp.LoginData

class SavedLoginViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val mutableLoginState =
        savedStateHandle.getLiveData<LoginData>("login", LoginData("", ""))

    val state: LiveData<LoginData>
        get() = mutableLoginState

    fun updateLogin(email: String, password: String){
        val data = LoginData(email, password)
        savedStateHandle["login"] = data
        mutableLoginState.value = data
    }

    fun isValidData(): Boolean{
        val data: LoginData? = state.value
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(data?.email).matches()
        val isValidPassword = data?.password?.length!! >= 8
        return isValidEmail && isValidPassword
    }
}