package com.pogrom.ktsapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pogrom.ktsapp.R
import com.pogrom.ktsapp.databinding.FragmentLoginBinding
import com.pogrom.ktsapp.models.SavedLoginViewModel
import com.pogrom.ktsapp.utils.autoCleared


class LoginFragment : Fragment(R.layout.fragment_login){
    private var  editTextLogin : EditText? = null
    private var editTextPassword : EditText? = null
    private var buttonLogin : Button? = null

    private val savedLoginViewModel: SavedLoginViewModel by viewModels()
    private var binding: FragmentLoginBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextLogin = binding.emailText
        editTextPassword = binding.passwordText
        buttonLogin = binding.loginButton
        editTextLogin?.addTextChangedListener(loginTextWatcher)
        editTextPassword?.addTextChangedListener(loginTextWatcher)

        savedLoginViewModel.state.observe(viewLifecycleOwner, {

            buttonLogin?.isEnabled = savedLoginViewModel.isValidData()
        })

        val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
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

        override fun afterTextChanged(p0: Editable?) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val loginInput = editTextLogin?.text.toString()
            val passwordInput = editTextPassword?.text.toString()

            savedLoginViewModel.updateLogin(loginInput, passwordInput)
        }
    }
}
