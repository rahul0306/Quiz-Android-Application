package com.example.completequizapp.model

import com.google.gson.annotations.SerializedName

data class Question(
    val question: String,
    @SerializedName("correct_answer") val correctAnswer: String,
    @SerializedName("incorrect_answers") val incorrectAnswers: List<String>
)
