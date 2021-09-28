package com.pogrom.ktsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pogrom.ktsapp.FeedDelegatesListAdapter
import com.pogrom.ktsapp.R
import com.pogrom.ktsapp.databinding.FragmentMainBinding
import com.pogrom.ktsapp.models.AdwItem
import com.pogrom.ktsapp.models.LoadingItem
import com.pogrom.ktsapp.models.PostItem
import com.pogrom.ktsapp.utils.autoCleared
import java.util.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private var feedAdapter: FeedDelegatesListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        loadMoreItems()
    }

    private fun initList() {
        feedAdapter = FeedDelegatesListAdapter()
        with(binding.feed) {
            val orientation = RecyclerView.VERTICAL
            adapter = feedAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

            // Pagination
            addOnScrollListener(
                PaginationScrollListener(
                    layoutManager = layoutManager as LinearLayoutManager,
                    requestNextItems = ::loadMoreItems,
                    visibilityThreshold = 3
                )
            )

            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun getDefaultItems() = List(20) {
        val randomUUID = UUID.randomUUID()
        when ((1..2).random()) {
            1 -> PostItem(
                userName = "Василий Подкоин",
                likesCount = 0,
                userAvatar = R.drawable.av_test,
                picture = R.drawable.im_test,
                uuid = randomUUID
            )
            2 -> AdwItem(
                image = R.drawable.ic_unsplash_logo_full,
                description = "Ничего лучше вы не видели!",
                uuid = randomUUID
            )
            else -> error("Wrong random number")
        }
    }

    private fun loadMoreItems() {
        val newItems = feedAdapter.items.toMutableList().apply {
            if (lastOrNull() is LoadingItem) {
                removeLastOrNull()
            }
        } + getDefaultItems() + LoadingItem()
        feedAdapter.items = newItems
        Log.d("Pagination", newItems.size.toString())
    }
}