package com.pogrom.ktsapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController


class LoginFragment : Fragment(R.layout.fragment_login){
    private var  editTextLogin : EditText? = null
    private var editTextPassword : EditText? = null
    private var buttonLogin : Button? = null

    private val savedLoginViewModel: SavedLoginViewModel? by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextLogin = view.findViewById(R.id.emailText)
        editTextPassword = view.findViewById(R.id.passwordText)
        buttonLogin = view.findViewById(R.id.loginButton)

        editTextLogin?.addTextChangedListener(loginTextWatcher)
        editTextPassword?.addTextChangedListener(loginTextWatcher)

        savedLoginViewModel?.state?.observe(viewLifecycleOwner, {
            buttonLogin?.isEnabled = savedLoginViewModel?.isValidData() == true
        })

        val action = LoginFragmentDirections.actionLoginFragment2ToMainFragment()
        buttonLogin?.setOnClickListener{
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        editTextLogin = null
        editTextPassword = null
        buttonLogin = null
    }

    private val loginTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val loginInput = editTextLogin?.text.toString()
            val passwordInput = editTextPassword?.text.toString()

            savedLoginViewModel?.updateLogin(loginInput, passwordInput)
        }

        override fun afterTextChanged(p0: Editable?) {}

    }
}
