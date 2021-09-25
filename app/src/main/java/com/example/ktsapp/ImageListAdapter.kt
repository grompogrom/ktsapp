package com.example.ktsapp

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ktsapp.databinding.ItemImageBinding

class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.ViewHolder>(){

    private var items = emptyList<ImageItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_image,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(newItems: List<ImageItem>) {
        items = newItems.toList()
        notifyDataSetChanged()
    }

    class ViewHolder(
        item: View
    ): RecyclerView.ViewHolder(item) {
        private val binding = ItemImageBinding.bind(item)

        fun bind(imageItem: ImageItem) = with(binding){
            avatarImage.setImageResource(imageItem.userAvatar)
            pictureView.setImageResource(imageItem.picture)
            userNameView.text = imageItem.userName
            likesCountView.text = imageItem.likesCount.toString()
            likeButton.setOnClickListener {
                imageItem.likesCount++
                likesCountView.text = imageItem.likesCount.toString() }
        }

    }
}


