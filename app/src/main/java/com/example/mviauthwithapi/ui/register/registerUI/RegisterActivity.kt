package com.example.mviauthwithapi.ui.register.registerUI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.mviauthwithapi.R
import com.example.mviauthwithapi.databinding.ActivityRegisterBinding
import com.example.mviauthwithapi.ui.Login.loginUI.LoginActivity
import com.example.mviauthwithapi.ui.main.MainActivity
import com.example.mviauthwithapi.ui.register.viewmodel.RegisterViewModel
import com.example.mviauthwithapi.ui.state.AuthState
import com.example.mviauthwithapi.util.isPasswordMatch
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
//use this Email to Register "eve.holt@reqres.in" With any password
@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private  val viewModel :RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.register.setOnClickListener {
            Register()
        }
        binding.login.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

        setViewModel()
    }
    //use this Email to Register "eve.holt@reqres.in" With any password
    private fun setViewModel(){
        lifecycleScope.launch {
            viewModel.authState.collect{ state ->
                when(state){
                    is AuthState.Error -> Toast.makeText(
                        this@RegisterActivity,
                        "Error:${state.error}",
                        Toast.LENGTH_LONG
                    ).show()
                    AuthState.Idle -> return@collect
                    AuthState.Loading -> return@collect
                    is AuthState.Success -> {
                        goToHome(state.message)
                    }
                }
            }
        }

    }
    //use this Email to Register "eve.holt@reqres.in" With any password
    private   fun goToHome(token: String) {
        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
        intent.putExtra("USER_TOKEN", token)
        startActivity(intent)
    }
    //use this Email to Register "eve.holt@reqres.in" With any password
    private fun Register() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val confirmPassword = binding.confirmPassword.text.toString()

        if (email.isEmpty()) {
            binding.userEmail.error = "Email cannot be empty"
            return
        }

        if (password.isEmpty()) {
            binding.userPassword.error = "Password cannot be empty"
            return
        }

        if (confirmPassword.isEmpty()) {
            binding.confirmPassword.error = "Confirm Password cannot be empty"
            return
        }

        if (isPasswordMatch(password, confirmPassword)) {
            viewModel.register(email, password)
        } else {
            binding.confirmPassword.error = "Passwords do not match"
        }
    }
}