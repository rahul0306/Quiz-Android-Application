package com.example.completequizapp.presentation.category

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.completequizapp.model.QuizCategory

@SuppressLint("ContextCastToActivity")
@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    fullName: String,
    onCategorySelected: (Int) -> Unit,
    onLogout: () -> Unit
) {
    val categories = QuizCategory.categories
    val exit = LocalContext.current as? Activity
    BackHandler {
        exit?.finish()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(insets = WindowInsets.statusBars)
            .padding(10.dp),
        verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Hi $fullName,", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Button(onClick = onLogout, shape = CircleShape) {
                Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = "Log out")
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Great to see you!")
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Select category",
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn {
            items(categories) { (name, categoryId) ->
                Button(
                    onClick = { onCategorySelected(categoryId) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = name,
                        modifier = Modifier.padding(17.dp),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}