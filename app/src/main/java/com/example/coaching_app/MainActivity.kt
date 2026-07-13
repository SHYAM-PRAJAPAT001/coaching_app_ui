package com.example.coaching_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coaching_app.ui.theme.Coaching_appTheme
import com.example.coaching_app.view.main.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Coaching_appTheme {
                MainScreen()
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)

fun MainScreenPreview(){
    MainScreen()
}