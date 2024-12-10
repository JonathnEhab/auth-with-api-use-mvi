package com.example.mviauthwithapi.ui.Login.loginUI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

import com.example.mviauthwithapi.databinding.ActivityLoginBinding
import com.example.mviauthwithapi.ui.Login.viewmodel.LoginViewModel
import com.example.mviauthwithapi.ui.main.MainActivity
import com.example.mviauthwithapi.ui.register.registerUI.RegisterActivity
import com.example.mviauthwithapi.ui.state.AuthState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
// use this Email to Login "eve.holt@reqres.in" With any password
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModel()
        binding.login.setOnClickListener {
            login()
        }
        binding.register.setOnClickListener {
            goToRegister()
        }


    }
    // use this Email to Login "eve.holt@reqres.in" With any password
    private fun setViewModel() {
        lifecycleScope.launch {
            viewModel.authState.collect { state ->
                when (state) {
                    is AuthState.Success -> {
                        goToHome(state.message)
                    }

                    is AuthState.Error -> Toast.makeText(
                        this@LoginActivity,
                        "Error:${state.error}",
                        Toast.LENGTH_LONG
                    ).show()
                    AuthState.Idle -> return@collect
                    AuthState.Loading -> return@collect
                }

            }
        }
    }
    // use this Email to Login "eve.holt@reqres.in" With any password
    private   fun goToHome(token: String) {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra("USER_TOKEN", token)
        startActivity(intent)
    }
    private fun goToRegister() {
        startActivity(
            Intent(
                this@LoginActivity,
                RegisterActivity::class.java
            )
        )
    }
    // use this Email to Login "eve.holt@reqres.in" With any password
    private fun login() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        if (email.isEmpty()) {
            binding.userEmail.error = "Please enter Email"
            return
        }

        if (password.isEmpty()) {
            binding.userPassword.error = "Please enter password"
            return
        }

        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.login(email, password)
        }
    }
}