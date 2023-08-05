package com.example.pharmamanufacturer.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pharmamanufacturer.presentation.COMPONENT_DETAILS_KEY
import com.example.pharmamanufacturer.presentation.Screen
import com.example.pharmamanufacturer.presentation.componentdetails.ComponentDetailsScreen
import com.example.pharmamanufacturer.presentation.components.ComponentsScreen
import com.example.pharmamanufacturer.presentation.packing.PackingScreen
import com.example.pharmamanufacturer.presentation.products.ProductsScreen

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
            ComponentsScreen(navController)
        }
        composable(Screen.PackingScreen.rout) {
            PackingScreen()
        }

        composable(
            route = Screen.ComponentDetailsScreen.rout + "/{$COMPONENT_DETAILS_KEY}",
            arguments = listOf(
                navArgument(COMPONENT_DETAILS_KEY) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            entry.arguments?.getString(COMPONENT_DETAILS_KEY)?.let { component ->
                ComponentDetailsScreen(component)
            }
        }
    }
}
