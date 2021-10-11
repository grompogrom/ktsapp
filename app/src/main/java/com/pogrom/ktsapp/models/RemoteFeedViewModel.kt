package com.pogrom.ktsapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pogrom.ktsapp.models.Responses.PostData
import com.pogrom.ktsapp.networking.Networking
import com.pogrom.ktsapp.networking.PostRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class RemoteFeedViewModel(
    private val repository: PostRepository = PostRepository((Networking.unsplashApiService))
) : ViewModel() {
    private var currentLoadingJob: Job? = null

    fun fetchPosts(): Flow<PagingData<PostData>> {
        Timber.d("run fetching")
        return repository.letPostsFlow()
            .cachedIn(viewModelScope)
    }

    fun likeAction(imageId: String){
        currentLoadingJob?.cancel()
        currentLoadingJob = viewModelScope.launch {

            runCatching {
                val post = repository.getPost(imageId)
                if (post.liked_by_user != null) {
                    if (post.liked_by_user){
                        repository.delLike(imageId)
                    }
                    else
                        repository.addLike(imageId)
                }
            }.onFailure {
                Timber.d(it)
            }.onSuccess {
            }
        }
    }
}