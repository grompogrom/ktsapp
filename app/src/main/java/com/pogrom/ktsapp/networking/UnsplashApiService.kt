package com.pogrom.ktsapp.networking

import com.pogrom.ktsapp.models.Responses.PostData
import retrofit2.http.*

interface UnsplashApiService {

    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page : Int,
        @Query("client_id") client_id: String = "m5jIoFCy8IyMBEvNQEURziyGmomJN-B-Y7uu3e8KRXY")
        :List<PostData>

    @POST("photos/{id}/like")
    suspend fun addLike(
        @Path("id") imageId : String
    ):PostData

    @DELETE("photos/{id}/like")
    suspend fun deleteLike(
        @Path("id") imageId : String
    ):PostData

    @GET("photos/{id}")
    suspend fun getPost(
        @Path("id") imageId: String
    ): PostData
}