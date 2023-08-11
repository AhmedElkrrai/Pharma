package com.example.pharmamanufacturer.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pharmamanufacturer.data.utils.ComponentType
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
        startDestination = Screen.ProductsScreen.route
    ) {
        composable(Screen.ProductsScreen.route) {
            ProductsScreen()
        }
        composable(Screen.ComponentsScreen.route) {
            ComponentsScreen { component ->
                navController.navigate(
                    Screen.ComponentDetailsScreen.withArgs(component.toString())
                )
            }
        }
        composable(Screen.PackingScreen.route) {
            PackingScreen()
        }

        composable(
            route = Screen.ComponentDetailsScreen.route + "/{$COMPONENT_DETAILS_KEY}",
            arguments = listOf(
                navArgument(COMPONENT_DETAILS_KEY) {
                    type = ComponentType
                    nullable = false
                }
            )
        ) {
            ComponentDetailsScreen {
                val destinationRoute = Screen.ComponentsScreen.route
                navController.navigate(destinationRoute) {
                    popUpTo(destinationRoute) {
                        inclusive = true
                    }
                }
            }
        }
    }
}
