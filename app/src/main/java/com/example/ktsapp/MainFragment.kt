package com.example.ktsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ktsapp.databinding.FragmentMainBinding
import java.util.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private var imageAdapter = ImageListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        setDefaultItems()
    }

    private fun initList() {
        imageAdapter = ImageListAdapter()
        with(binding.feed) {
            val orientation = RecyclerView.VERTICAL
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)
            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun setDefaultItems() {
        val defaultItems = List(20) {
            val randomUUID = UUID.randomUUID()
            ImageItem(
                userName = "Vlad",
                userAvatar = R.drawable.av_test,
                picture = R.drawable.im_test,
                likesCount = 0
            )
        }
        imageAdapter.setItems(defaultItems)
    }
}
