package com.pogrom.ktsapp.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogrom.ktsapp.models.Responses.GetPhotosResponse
import com.pogrom.ktsapp.networking.PostRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FeedListViewModel: ViewModel() {

    private val repository = PostRepository()

    private val feedListLiveData = MutableLiveData<List<Any>>(emptyList())
    private val isLoadingLiveData = MutableLiveData(false)

    private var currentLoadingJob: Job? = null

    val userList: LiveData<List<Any>>
        get() = feedListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun getPosts(page: Int){
        isLoadingLiveData.postValue(true)
        currentLoadingJob?.cancel()
        currentLoadingJob = viewModelScope.launch {
            runCatching {
                repository.getPhotos(1)
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                feedListLiveData.postValue(feedListLiveData.value?.plus(it)?.plus(LoadingItem()))
            }.onFailure {
                Log.e("err", it.toString())
                isLoadingLiveData.postValue(false)
                feedListLiveData.postValue(feedListLiveData.value?.plus(LoadingItem()))
            }
        }
    }

}