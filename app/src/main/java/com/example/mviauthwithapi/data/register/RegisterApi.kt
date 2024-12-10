package com.example.mviauthwithapi.data.register

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("api/register")
    suspend fun register(@Body registerRequest: RegisterRequest):Response<RegisterResponse>
}