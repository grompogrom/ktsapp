package com.example.ktsapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController


class LoginFragment : Fragment(R.layout.fragment_login){
    private var  editTextLogin : EditText? = null
    private var editTextPassword : EditText? = null
    private var buttonLogin : Button? = null

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextLogin = view.findViewById(R.id.emailText)
        editTextPassword = view.findViewById(R.id.passwordText)
        buttonLogin = view.findViewById(R.id.loginButton)
        editTextLogin?.addTextChangedListener(loginTextWatcher)
        editTextPassword?.addTextChangedListener(loginTextWatcher)

        loginViewModel.state.observe(viewLifecycleOwner, { state ->
            val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(state.email).matches()
            val isValidPassword = state.password.length >= 8

            buttonLogin?.isEnabled = isValidPassword && isValidEmail
        })

        val action = LoginFragmentDirections.actionLoginFragment2ToMainFragment()
        buttonLogin?.setOnClickListener{
            findNavController().navigate(action)
        }
    }

    private val loginTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val loginInput = editTextLogin?.text.toString()
            val passwordInput = editTextPassword?.text.toString()

            loginViewModel.updateLogin(loginInput, passwordInput)
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    }
}
