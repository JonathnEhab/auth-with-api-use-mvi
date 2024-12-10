package com.example.mviauthwithapi.ui.Login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mviauthwithapi.ui.Login.loginRepo.LoginRepo
import com.example.mviauthwithapi.ui.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
//use this Email to Register "eve.holt@reqres.in" With any password
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepo: LoginRepo) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = loginRepo.login(email, password)
                _authState.value = AuthState.Success("Token : ${response.body()!!.token}")
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.localizedMessage ?: "Error occurred")
            }
        }
    }
}