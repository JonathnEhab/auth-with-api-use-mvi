package com.example.mviauthwithapi.di


import com.example.mviauthwithapi.data.login.LoginApi
import com.example.mviauthwithapi.data.register.RegisterApi
import com.example.mviauthwithapi.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
   fun provideLoginApi(retrofit: Retrofit):LoginApi{
       return retrofit.create(LoginApi::class.java)
   }
    @Provides
    @Singleton
    fun provideRegisterApi(retrofit: Retrofit):RegisterApi{
        return retrofit.create(RegisterApi::class.java)
    }
}