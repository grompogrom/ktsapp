package com.pogrom.ktsapp.fragments

import  android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pogrom.ktsapp.FeedDelegatesListAdapter
import com.pogrom.ktsapp.R
import androidx.lifecycle.Observer
import com.pogrom.ktsapp.databinding.FragmentMainBinding
import com.pogrom.ktsapp.models.AdwItem
import com.pogrom.ktsapp.models.FeedListViewModel
import com.pogrom.ktsapp.models.LoadingItem
import com.pogrom.ktsapp.models.PostItem
import com.pogrom.ktsapp.utils.PaginationScrollListener
import com.pogrom.ktsapp.utils.autoCleared
import java.util.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: FeedListViewModel by viewModels()

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private var feedAdapter: FeedDelegatesListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
        loadMoreItems()
    }

    private fun initList() {
        feedAdapter = FeedDelegatesListAdapter()
        with(binding.feed) {
            val orientation = RecyclerView.VERTICAL
            adapter = feedAdapter
                layoutManager = LinearLayoutManager(context, orientation, false)

//          Pagination
            addOnScrollListener(
                PaginationScrollListener(
                    layoutManager = layoutManager as LinearLayoutManager,
                    requestNextItems = ::loadMoreItems,
                    visibilityThreshold = 6
                )
            )

            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }



    private fun bindViewModel() {
        viewModel.userList.observe(viewLifecycleOwner, Observer { feedAdapter.items = it })

    }

    private fun loadMoreItems(page: Int = 3) {
        viewModel.getPosts(page)
    }
}