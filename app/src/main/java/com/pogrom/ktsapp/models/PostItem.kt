package com.pogrom.ktsapp.models

import java.util.*


data class PostItem(
    val userName: String,
    val userAvatar: Int,
    val picture: Int,
    var likesCount: Int,
    val uuid: UUID
)
