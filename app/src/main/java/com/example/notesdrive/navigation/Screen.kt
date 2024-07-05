package com.example.notesdrive.navigation

sealed class Screen(val route: String) {
    object Main: Screen(route = "main_screen")
    object AddScreen: Screen(route = "add_screen")
    object UpdateScreen: Screen(route = "update_screen")
    object CardScreen: Screen(route = "card_screen")
}