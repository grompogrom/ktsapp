package com.pogrom.ktsapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pogrom.ktsapp.R
import com.pogrom.ktsapp.WelcomeScreenData
import com.pogrom.ktsapp.WelcomeScreenPagerAdapter
import com.pogrom.ktsapp.databinding.FragmentWelcomeScreenBinding


class WelcomeScreenFragment : Fragment(R.layout.fragment_welcome_screen) {
    private val binding: FragmentWelcomeScreenBinding by viewBinding(FragmentWelcomeScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val action = WelcomeScreenFragmentDirections.actionWelcomeScreenToLoginFragment2()
        val navController = findNavController()
        binding.apply { viewpager.adapter = WelcomeScreenPagerAdapter(getWelcomeScreenDataList(), navController, action)
        circleIndicator.setViewPager(viewpager)
        }
    }
    private fun getWelcomeScreenDataList(): List<WelcomeScreenData> {
        return listOf(
            WelcomeScreenData(
                title = getString(R.string.welcomeScreen_title1),
                getString(R.string.welcomeScreen_description1)
            ),
            WelcomeScreenData(
                title = getString(R.string.welcomeScreen_title2),
                getString(R.string.welcomeScreen_description2)
            ),
            WelcomeScreenData(
                title = getString(R.string.welcomeScreen_title3),
                getString(R.string.welcomeScreen_description3)
            )
        )
    }
}
