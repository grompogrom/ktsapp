package com.example.ktsapp.item_delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ktsapp.R
import com.example.ktsapp.databinding.ItemAdwBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import com.example.ktsapp.models.AdwItem

class AdwItemDelegate(
    private val onItemClick: (item: AdwItem) -> Unit
) : AbsListItemAdapterDelegate<Any, Any, AdwItemDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is AdwItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_adw, parent, false)
        return ViewHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as AdwItem)
    }

    inner class ViewHolder(
        override val containerView: View,
        private val onItemClick: (item: AdwItem) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val binding = ItemAdwBinding.bind(containerView)

        private var currentItem: AdwItem? = null

        init {
            containerView.setOnClickListener { currentItem?.let(onItemClick) }
        }

        fun bind(item: AdwItem) = with(binding) {
            description.text = item.description
            imageAdw.setImageResource(item.image)
        }
    }
}