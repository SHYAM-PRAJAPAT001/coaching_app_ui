package com.example.coaching_app.navigation

sealed class Routes(val route : String) {

    object LOGIN : Routes("login")
    object HOME : Routes("home")
    object COURSES : Routes("courses")
    object PROFILE : Routes("profile")
    object DOWNLOAD : Routes("download")
    object LIVE : Routes("live")

}