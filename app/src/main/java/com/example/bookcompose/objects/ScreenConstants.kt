package com.example.bookcompose.objects

sealed class ScreenConstants(val route: String){
    object HomeScreen : ScreenConstants("home_screen")
}