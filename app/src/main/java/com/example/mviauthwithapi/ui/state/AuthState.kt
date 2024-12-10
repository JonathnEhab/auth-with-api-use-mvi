package com.example.mviauthwithapi.ui.state

import java.lang.Error

sealed class AuthState {
    object Idle:AuthState()
    object Loading : AuthState()
    data class Success(val message: String):AuthState()
    data class Error(val error: String): AuthState()

}