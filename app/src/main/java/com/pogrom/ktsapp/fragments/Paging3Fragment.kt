package com.pogrom.ktsapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pogrom.ktsapp.R
import com.pogrom.ktsapp.RemoteFeedAdapter
import com.pogrom.ktsapp.databinding.FragmentMainBinding
import com.pogrom.ktsapp.models.RemoteFeedViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import timber.log.Timber

class Paging3Fragment : Fragment(R.layout.fragment_main) {


    lateinit var feedViewModel: RemoteFeedViewModel

    lateinit var feedAdapter: RemoteFeedAdapter

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.plant(Timber.DebugTree())
        Timber.d("View created")
        initList()
        fetchPosts()
    }

    private fun initList() {
        feedAdapter = RemoteFeedAdapter()
        with(binding.feed) {
            val orientation = RecyclerView.VERTICAL
            adapter = feedAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

        }
        feedViewModel = defaultViewModelProviderFactory.create(RemoteFeedViewModel::class.java)
    }

    private fun fetchPosts(){
        lifecycleScope.launch{
            feedViewModel.fetchDoggoImages().distinctUntilChanged().collectLatest {
                feedAdapter.submitData(it)
            }
        }
    }


}