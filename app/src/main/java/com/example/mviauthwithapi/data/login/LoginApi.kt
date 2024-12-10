package com.example.mviauthwithapi.data.login

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest):Response<LoginResponse>
}