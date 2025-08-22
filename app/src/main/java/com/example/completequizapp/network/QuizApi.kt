package com.example.completequizapp.network

import com.example.completequizapp.model.QuizResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {
    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount")
        amount: Int,

        @Query("category")
        category: Int? = null,

        @Query("difficulty")
        difficulty: String? = null,

        @Query("type")
        type: String? = null
    ): QuizResponse
}