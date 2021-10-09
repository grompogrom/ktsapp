package com.pogrom.ktsapp.models.Responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val bio: String?,
    val id: String,
    val name: String,
    val profile_image: ProfileImage,
    val username: String
)