package com.pogrom.ktsapp.item_delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.pogrom.ktsapp.R
import com.pogrom.ktsapp.databinding.ItemPostBinding
import com.pogrom.ktsapp.models.Responses.GetPhotosResponse as PostItem // fixme
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
            Glide.with(itemView)
                .load(postItem.user.profile_image.small)
                .transform(CircleCrop())
                .placeholder(R.drawable.av_test)
                .into(binding.avatarImage)

            Glide.with(itemView)
                .load(postItem.urls.full)
                .placeholder(R.drawable.im_test)
                .into(binding.pictureView)

            userNameView.text = postItem.user.name
            likesCountView.text = postItem.likes.toString()
            likeButton.setOnClickListener {}
        }
    }
}