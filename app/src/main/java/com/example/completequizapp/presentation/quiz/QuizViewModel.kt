package com.example.completequizapp.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.completequizapp.network.QuizApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val api: QuizApi
) : ViewModel() {
    private val _uiState = MutableStateFlow<QuizState>(QuizState.Loading)
    val uiState: StateFlow<QuizState> = _uiState

    fun fetchQuestions(category: Int?, difficulty: String?, type: String? = null) {
        viewModelScope.launch {
            _uiState.value = QuizState.Loading
            try {
                val result = api.getQuestions(
                    amount = 10,
                    category = category,
                    difficulty = difficulty,
                    type = type
                )
                _uiState.value = QuizState.Success(result.results)
            } catch (e: Exception) {
                _uiState.value = QuizState.Error(e.localizedMessage ?: "Something went wrong")
            }
        }
    }
}