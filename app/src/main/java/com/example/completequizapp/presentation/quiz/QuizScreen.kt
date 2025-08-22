package com.example.completequizapp.presentation.quiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = hiltViewModel(),
    onComplete: (Int) -> Unit
) {
    BackHandler(enabled = true) { }
    val uiState by viewModel.uiState.collectAsState()
    var index by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var selected by remember { mutableStateOf<String?>(null) }

    when (val state = uiState) {
        is QuizState.Error -> Text(text = "Error ${state.message}")
        is QuizState.Loading -> CircularProgressIndicator()
        is QuizState.Success -> {
            val question = state.question[index]
            val incorrect = question.incorrectAnswers ?: emptyList()
            val correct = question.correctAnswer ?: ""
            val options = (incorrect + correct)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    progress = { (index + 1) / 10f },
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = question.question, fontWeight = FontWeight.Bold, fontSize = 23.sp)
                Spacer(modifier = Modifier.height(10.dp))
                options.forEach {
                    Button(onClick = { selected = it }, shape = RoundedCornerShape(10.dp)) {
                        Text(
                            text = it
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        if (selected == question.correctAnswer) score++
                        if (index == 9) onComplete(score) else {
                            selected = null
                            index++
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.5f),
                    shape = RoundedCornerShape(10.dp),
                    enabled = selected != null
                ) {
                    Text(text = if (index == 9) "Finish" else "Next")
                }
            }
        }
    }
}