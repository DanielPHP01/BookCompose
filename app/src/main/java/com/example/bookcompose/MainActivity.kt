package com.example.bookcompose

import Navigation
import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.bookcompose.ui.theme.BookComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookComposeTheme {
                Navigation()
            }
        }
    }
}