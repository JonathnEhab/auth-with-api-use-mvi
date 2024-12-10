package com.example.mviauthwithapi.ui.register.registerRepo

import com.example.mviauthwithapi.data.register.RegisterApi
import com.example.mviauthwithapi.data.register.RegisterRequest
import com.example.mviauthwithapi.data.register.RegisterResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterImpl @Inject constructor(private val registerApi: RegisterApi): RegisterRepo {
    override suspend fun register(email: String, password: String): Response<RegisterResponse> {
        return registerApi.register(RegisterRequest(email,password))
    }
}