package com.example.pharmamanufacturer.presentaion.home_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pharmamanufacturer.presentaion.Screen
import com.example.pharmamanufacturer.presentaion.components_screen.ComponentsScreen
import com.example.pharmamanufacturer.presentaion.packing_screen.PackingScreen
import com.example.pharmamanufacturer.presentaion.products_screen.ProductsScreen

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
