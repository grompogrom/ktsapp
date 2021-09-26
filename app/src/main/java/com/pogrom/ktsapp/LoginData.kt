package com.pogrom.ktsapp
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginData(
    val email: String,
    val password: String
) : Parcelable
