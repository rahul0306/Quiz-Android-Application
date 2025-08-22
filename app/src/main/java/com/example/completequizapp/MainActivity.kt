package com.example.completequizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.completequizapp.navigation.QuizNavigation
import com.example.completequizapp.presentation.login.LoginScreen
import com.example.completequizapp.ui.theme.CompleteQuizAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompleteQuizAppTheme {
                QuizNavigation()
            }
        }
    }
}
