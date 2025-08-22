package com.example.completequizapp.di

import com.example.completequizapp.network.QuizApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuizApi(retrofit: Retrofit): QuizApi =
        retrofit.create(QuizApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
}