package com.pogrom.ktsapp.item_delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pogrom.ktsapp.R
import com.pogrom.ktsapp.databinding.ItemPostBinding
import com.pogrom.ktsapp.models.PostItem
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer

class PostItemDelegate : AbsListItemAdapterDelegate<Any, Any, PostItemDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is PostItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as PostItem)
    }

    inner class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val binding = ItemPostBinding.bind(containerView)

        fun bind(postItem: PostItem) = with(binding){
            avatarImage.setImageResource(postItem.userAvatar)
            pictureView.setImageResource(postItem.picture)
            userNameView.text = postItem.userName
            likesCountView.text = postItem.likesCount.toString()
            likeButton.setOnClickListener {
                postItem.likesCount++
                likesCountView.text = postItem.likesCount.toString() }
        }
    }
}