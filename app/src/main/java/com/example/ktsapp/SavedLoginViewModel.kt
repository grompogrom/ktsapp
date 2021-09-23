package com.example.ktsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

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

}