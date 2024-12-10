package com.example.mviauthwithapi.ui.Login.loginRepo

import com.example.mviauthwithapi.data.login.LoginResponse
import retrofit2.Response

interface LoginRepo  {
    suspend fun login(email:String,password:String):Response<LoginResponse>
}