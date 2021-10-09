package com.pogrom.ktsapp.networking

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pogrom.ktsapp.PostPagingSource
import com.pogrom.ktsapp.models.Responses.GetPhotosResponse
import kotlinx.coroutines.flow.Flow
import com.pogrom.ktsapp.networking.UnsplashApiService

class PostRepository(
    private val unsplashApiService: UnsplashApiService = Networking.unsplashApiService // I'm not sure
) {
    // useless for paging3
    suspend fun getPhotos(page: Int):List<GetPhotosResponse>{
        return Networking.unsplashApiService.getPhotos(page = page)
    }

    fun letPostsFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<GetPhotosResponse>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { PostPagingSource(unsplashApiService) }
        ).flow
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 10, enablePlaceholders = false)
    }
}