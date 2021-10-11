package com.pogrom.ktsapp.networking

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pogrom.ktsapp.PostPagingSource
import com.pogrom.ktsapp.models.Responses.PostData
import kotlinx.coroutines.flow.Flow

class PostRepository(
    private val unsplashApiService: UnsplashApiService = Networking.unsplashApiService // I'm not sure
) {

    fun letPostsFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<PostData>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { PostPagingSource(unsplashApiService) }
        ).flow
    }

    suspend fun addLike(imageId: String) =
        Networking.unsplashApiService.addLike(imageId)

    suspend fun delLike(imageId: String) =
        Networking.unsplashApiService.deleteLike(imageId)

    suspend fun getPost(imageId: String) =
        Networking.unsplashApiService.getPost(imageId)


    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 10, enablePlaceholders = false)
    }
}

