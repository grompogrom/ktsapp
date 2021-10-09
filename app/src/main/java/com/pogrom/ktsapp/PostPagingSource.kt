package com.pogrom.ktsapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pogrom.ktsapp.models.Responses.GetPhotosResponse
import com.pogrom.ktsapp.networking.UnsplashApiService
import timber.log.Timber
import java.lang.Exception

class PostPagingSource(
    private val unsplashApiService: UnsplashApiService,

    ): PagingSource<Int, GetPhotosResponse>() {
    override fun getRefreshKey(state: PagingState<Int, GetPhotosResponse>): Int? {
        Timber.d("OK")
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetPhotosResponse> {
         try {
            val page: Int = params.key ?: 1
            val pageSize : Int = params.loadSize
            val data = unsplashApiService.getPhotos(page)
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (data.isEmpty()) null else page + 1
             Timber.d("It's ok")
            return LoadResult.Page(
                data,
                prevKey=prevKey,
                nextKey=nextKey)
        } catch (e: Exception){
            Timber.d("It's Bad")
             Timber.e(e)
            return LoadResult.Error(e)
        }
    }
}