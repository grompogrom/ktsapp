package com.pogrom.ktsapp.models.Responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetPhotosResponse(
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: String?,
    val id: String,
    val liked_by_user: Boolean?,
    val likes: Int,
    val links: Links,
    val updated_at: String,
    val urls: Urls,
    val user: User
)