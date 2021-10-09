package com.pogrom.ktsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.pogrom.ktsapp.databinding.ItemPostBinding
import com.pogrom.ktsapp.models.Responses.GetPhotosResponse
import timber.log.Timber

class RemoteFeedAdapter() :
    PagingDataAdapter<GetPhotosResponse, PostViewHolder>(RemoteDiffCallback) {


    companion object RemoteDiffCallback : DiffUtil.ItemCallback<GetPhotosResponse>() {
        override fun areItemsTheSame(
            oldItem: GetPhotosResponse,
            newItem: GetPhotosResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: GetPhotosResponse,
            newItem: GetPhotosResponse
        ): Boolean {
            return  oldItem == newItem
        }


    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        Timber.d("try to bind view holder")
        getItem(position)?.let { (holder as? PostViewHolder)?.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_post,
            parent,
            false
        )
        Timber.d("View holder created")
        return PostViewHolder(view)
    }
}

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemPostBinding.bind(itemView)

    fun bind(postItem: GetPhotosResponse) = with(binding){
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
        likeButton.setOnClickListener {}
    }
}
