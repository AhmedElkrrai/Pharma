package com.example.pharmamanufacturer.presentaion.home_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pharmamanufacturer.presentaion.components_screen.ComponentsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "chat") {
        composable("chat") {
            ChatScreen()
        }
        composable("components") {
            ComponentsScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
    }
}
