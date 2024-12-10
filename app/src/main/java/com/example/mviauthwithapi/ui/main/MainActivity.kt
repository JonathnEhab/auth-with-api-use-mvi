package com.example.mviauthwithapi.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mviauthwithapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
//use this Email to Register "eve.holt@reqres.in" With any password
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra("USER_TOKEN")
        binding.userToken.text = token
    }
}