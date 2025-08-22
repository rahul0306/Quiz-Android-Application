package com.example.completequizapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.completequizapp.presentation.category.CategoryScreen
import com.example.completequizapp.presentation.difficulty.DifficultyScreen
import com.example.completequizapp.presentation.login.LoginScreen
import com.example.completequizapp.presentation.quiz.QuizScreen
import com.example.completequizapp.presentation.quiz.QuizViewModel
import com.example.completequizapp.presentation.result.ResultScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun QuizNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentUser = FirebaseAuth.getInstance().currentUser
    val startDest = if (currentUser != null) {
        val fullName = currentUser.displayName ?: "User"
        "${QuizScreens.CATEGORY_SCREEN}/$fullName"
    } else {
        QuizScreens.LOGIN_SCREEN
    }
    NavHost(navController = navController, startDestination = startDest) {
        composable(route = QuizScreens.LOGIN_SCREEN) {
            LoginScreen { fullName ->
                navController.navigate("${QuizScreens.CATEGORY_SCREEN}/$fullName") {
                    popUpTo(QuizScreens.LOGIN_SCREEN) { inclusive = true }
                }
            }
        }
        composable(route = "${QuizScreens.CATEGORY_SCREEN}/{fullName}") { backStackEntry ->
            val fullName = backStackEntry.arguments?.getString("fullName") ?: "User"
            CategoryScreen(fullName = fullName, onCategorySelected = { categoryId ->
                navController.navigate("${QuizScreens.DIFFICULTY_SCREEN}/$categoryId/$fullName")
            }) {
                FirebaseAuth.getInstance().signOut()
                navController.navigate(route = QuizScreens.LOGIN_SCREEN) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop
                }
            }
        }
        composable(route = "${QuizScreens.DIFFICULTY_SCREEN}/{categoryId}/{fullName}") { backStackEntry ->
            val fullName = backStackEntry.arguments?.getString("fullName") ?: "User"
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull() ?: 9
            DifficultyScreen(categoryId = categoryId) { selectedDifficulty ->
                navController.navigate(route = "${QuizScreens.QUIZ_SCREEN}/$categoryId/$selectedDifficulty/$fullName")
            }
        }
        composable(route = "${QuizScreens.QUIZ_SCREEN}/{categoryId}/{difficulty}/{fullName}") { backStackEntry ->
            val fullName = backStackEntry.arguments?.getString("fullName") ?: "User"
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull() ?: 9
            val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "easy"
            val viewModel: QuizViewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                viewModel.fetchQuestions(category = categoryId, difficulty = difficulty)
            }
            QuizScreen(viewModel = viewModel, onComplete = { score ->
                navController.navigate(route = "${QuizScreens.RESULT_SCREEN}/$score/$fullName"){
                    popUpTo(0){
                        inclusive = true
                    }
                }
            })
        }
        composable(route = "${QuizScreens.RESULT_SCREEN}/{score}/{fullName}") { backStackEntry ->
            val fullName = backStackEntry.arguments?.getString("fullName") ?: "User"
            val score = backStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            ResultScreen(score = score) {
                navController.popBackStack(route = QuizScreens.LOGIN_SCREEN, inclusive = true)
                navController.navigate("${QuizScreens.CATEGORY_SCREEN}/$fullName")
            }
        }
    }
}