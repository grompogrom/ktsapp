package com.pogrom.ktsapp.networking

import com.pogrom.ktsapp.models.Responses.GetPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {

    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page : Int,
        @Query("client_id") client_id: String = "m5jIoFCy8IyMBEvNQEURziyGmomJN-B-Y7uu3e8KRXY")
        :List<GetPhotosResponse>
}