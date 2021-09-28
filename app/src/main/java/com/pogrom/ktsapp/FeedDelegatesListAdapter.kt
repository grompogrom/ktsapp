package com.pogrom.ktsapp

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.pogrom.ktsapp.item_delegates.AdwItemDelegate
import com.pogrom.ktsapp.item_delegates.PageLoadingDelegate
import com.pogrom.ktsapp.item_delegates.PostItemDelegate
import com.pogrom.ktsapp.models.AdwItem
import com.pogrom.ktsapp.models.PostItem
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class FeedDelegatesListAdapter : AsyncListDifferDelegationAdapter<Any>(ComplexDiffCallback()) {

    init {
        delegatesManager
            .addDelegate(PostItemDelegate())
            .addDelegate(AdwItemDelegate())
            .addDelegate(PageLoadingDelegate())
    }

    class ComplexDiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areItemsTheSame(first: Any, second: Any): Boolean {
            return first.javaClass == second.javaClass && when (first) {
                is PostItem -> first.uuid == (second as PostItem).uuid
                is AdwItem -> first.uuid == (second as AdwItem).uuid
                else -> true
            }
        }
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second

    }


}