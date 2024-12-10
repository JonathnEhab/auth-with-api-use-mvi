package com.example.mviauthwithapi.ui.register.registerRepo

import com.example.mviauthwithapi.data.register.RegisterResponse
import retrofit2.Response

interface RegisterRepo {
    suspend fun register(email:String,password:String):Response<RegisterResponse>
}