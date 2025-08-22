package com.example.completequizapp.model

import com.google.gson.annotations.SerializedName

data class QuizResponse(
    @SerializedName("results") val results : List<Question>
)