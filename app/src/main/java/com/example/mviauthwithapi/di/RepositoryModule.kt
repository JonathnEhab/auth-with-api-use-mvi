package com.example.mviauthwithapi.di

import com.example.mviauthwithapi.data.login.LoginApi
import com.example.mviauthwithapi.data.register.RegisterApi
import com.example.mviauthwithapi.ui.Login.loginRepo.LoginImpl
import com.example.mviauthwithapi.ui.Login.loginRepo.LoginRepo
import com.example.mviauthwithapi.ui.register.registerRepo.RegisterImpl
import com.example.mviauthwithapi.ui.register.registerRepo.RegisterRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideLoginRepo(loginApi: LoginApi): LoginRepo {
        return LoginImpl(loginApi)
    }

    @Provides
    @Singleton
    fun provideRegisterRepo(registerApi: RegisterApi): RegisterRepo {
        return RegisterImpl(registerApi)
    }
}