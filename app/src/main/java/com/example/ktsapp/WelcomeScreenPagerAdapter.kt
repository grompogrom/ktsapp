package com.example.ktsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import com.example.ktsapp.databinding.ItemWelcomeScreenDescriptionBinding

class WelcomeScreenPagerAdapter(private val data: List<WelcomeScreenData>,
                                private val navController: NavController,
                                private val action: NavDirections
) : RecyclerView.Adapter<WelcomeScreenPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemWelcomeScreenDescriptionBinding.bind(itemView)

        fun bind(welcomeScreenData: WelcomeScreenData) = with(binding){
            welcomeText.text = welcomeScreenData.title
            descriptonText.text = welcomeScreenData.description
            buttonNext.setOnClickListener { navController.navigate(action) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_welcome_screen_description, parent, false)
        return Pager2ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}