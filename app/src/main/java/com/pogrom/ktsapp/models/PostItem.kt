package com.pogrom.ktsapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*


@JsonClass(generateAdapter = true)
data class PostItem(
    val userName: String,
    val userAvatar: Int,
    val picture: Int,
    @Json(name = "likes")
    var likesCount: Int,
    val uuid: UUID
)
