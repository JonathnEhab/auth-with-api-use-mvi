package com.example.mviauthwithapi.ui.Login.loginRepo

import com.example.mviauthwithapi.data.login.LoginApi
import com.example.mviauthwithapi.data.login.LoginRequest
import com.example.mviauthwithapi.data.login.LoginResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LoginImpl @Inject constructor(private val loginApi: LoginApi): LoginRepo {
    override suspend fun login(email: String, password: String): Response<LoginResponse> {
        return loginApi.login(LoginRequest(email,password))
    }
}