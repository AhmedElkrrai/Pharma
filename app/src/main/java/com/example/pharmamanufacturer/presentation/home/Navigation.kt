package com.example.pharmamanufacturer.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pharmamanufacturer.core.ComponentType
import com.example.pharmamanufacturer.core.COMPONENT_DETAILS_KEY
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.addcomponent.AddComponentScreen
import com.example.pharmamanufacturer.presentation.componentdetails.ComponentDetailsScreen
import com.example.pharmamanufacturer.presentation.components.ComponentsScreen
import com.example.pharmamanufacturer.presentation.packing.PackagingScreen
import com.example.pharmamanufacturer.presentation.products.ProductsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductsScreen.route
    ) {
        composable(route = Screen.ProductsScreen.route) {
            ProductsScreen()
        }
        composable(route = Screen.ComponentsScreen.route) {
            ComponentsScreen(
                onItemClick = { component ->
                    navController.navigate(
                        Screen.ComponentDetailsScreen.withArgs(component.toString())
                    )
                },
                onAddClick = {
                    navController.navigate(
                        Screen.AddComponentScreen.route
                    )
                }
            )
        }
        composable(route = Screen.PackagingScreen.route) {
            PackagingScreen()
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
                navigateToParent(
                    controller = navController,
                    parentRoute = Screen.ComponentsScreen.route
                )
            }
        }
        composable(route = Screen.AddComponentScreen.route) {
            AddComponentScreen {
                navigateToParent(
                    controller = navController,
                    parentRoute = Screen.ComponentsScreen.route
                )
            }
        }
    }
}
