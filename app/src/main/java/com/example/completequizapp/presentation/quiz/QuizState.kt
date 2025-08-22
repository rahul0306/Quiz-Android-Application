package com.example.completequizapp.presentation.quiz

import com.example.completequizapp.model.Question

sealed class QuizState {
    object Loading : QuizState()
    data class Success(val question: List<Question>) : QuizState()
    data class Error(val message: String) : QuizState()
}