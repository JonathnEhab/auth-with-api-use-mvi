package com.example.mviauthwithapi.util

fun isPasswordMatch(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}