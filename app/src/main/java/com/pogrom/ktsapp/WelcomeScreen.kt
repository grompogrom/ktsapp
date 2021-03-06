package com.pogrom.ktsapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class WelcomeScreen : Fragment(R.layout.fragment_welcome_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val skipButton = view.findViewById<Button>(R.id.buttonNext)
        val action = WelcomeScreenDirections.actionWelcomeScreenToLoginFragment2()
        skipButton?.setOnClickListener{
            findNavController().navigate(action)
        }
    }
}