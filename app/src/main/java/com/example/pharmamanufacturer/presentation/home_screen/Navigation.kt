package com.example.pharmamanufacturer.presentation.home_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pharmamanufacturer.presentation.Screen
import com.example.pharmamanufacturer.presentation.components_screen.ComponentsScreen
import com.example.pharmamanufacturer.presentation.packing_screen.PackingScreen
import com.example.pharmamanufacturer.presentation.products_screen.ProductsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductsScreen.rout
    ) {
        composable(Screen.ProductsScreen.rout) {
            ProductsScreen()
        }
        composable(Screen.ComponentsScreen.rout) {
            ComponentsScreen()
        }
        composable(Screen.PackingScreen.rout) {
            PackingScreen()
        }
    }
}
