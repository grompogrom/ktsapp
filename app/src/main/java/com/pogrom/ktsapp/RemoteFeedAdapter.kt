package com.pogrom.ktsapp

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.pogrom.ktsapp.databinding.ItemPostBinding
import com.pogrom.ktsapp.models.Responses.PostData
import timber.log.Timber


class RemoteFeedAdapter(
    private val onLikeClick: (item: PostData) -> Unit
) :
    PagingDataAdapter<PostData, PostViewHolder>(RemoteDiffCallback) {

    companion object RemoteDiffCallback : DiffUtil.ItemCallback<PostData>() {
        override fun areItemsTheSame(
            oldItem: PostData,
            newItem: PostData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PostData,
            newItem: PostData
        ): Boolean {
            return  oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        Timber.d("try to bind view holder")
        getItem(position)?.let { (holder as? PostViewHolder)?.bind(it) }
        RemoteFeedAdapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_post,
            parent,
            false
        )
        Timber.d("View holder created")
        return PostViewHolder(view, onLikeClick)
    }
}

class PostViewHolder(
    itemView: View,
    private val onLikeClick: (item: PostData) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemPostBinding.bind(itemView)
    private var isLiked : Boolean = false

    fun bind(postItem: PostData) = with(binding){
        
        isLiked = postItem.liked_by_user == true
        Glide.with(itemView)
            .load(postItem.user.profile_image.small)
            .transform(CircleCrop())
            .placeholder(R.drawable.av_test)
            .into(binding.avatarImage)

        binding.pictureView

        Glide.with(itemView)
            .load(postItem.urls.regular)
            .fitCenter()
            .placeholder(R.drawable.im_test)
            .into(binding.pictureView)

        userNameView.text = postItem.user.name
        likesCountView.text = postItem.likes.toString()
        likeButton.imageTintMode = PorterDuff.Mode.DST_IN
        likeButton.setOnClickListener {
           onLikeClick(postItem)
            isLiked = !isLiked
            if (isLiked) {
                likesCountView.text = postItem.likes.plus(1).toString()
                likeButton.imageTintMode = PorterDuff.Mode.MULTIPLY
            }
            else {
                likesCountView.text = postItem.likes.toString()
                likeButton.imageTintMode = PorterDuff.Mode.DST_IN
            }
        }
    }
}
