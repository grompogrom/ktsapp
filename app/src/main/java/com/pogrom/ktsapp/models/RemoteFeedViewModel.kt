package com.pogrom.ktsapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pogrom.ktsapp.models.Responses.GetPhotosResponse
import com.pogrom.ktsapp.networking.Networking
import com.pogrom.ktsapp.networking.PostRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class RemoteFeedViewModel(
    private val repository: PostRepository = PostRepository((Networking.unsplashApiService))
) : ViewModel() {

    fun fetchDoggoImages(): Flow<PagingData<GetPhotosResponse>> {
        Timber.d("run fetching")
        return repository.letPostsFlow()
            .cachedIn(viewModelScope)
    }

}