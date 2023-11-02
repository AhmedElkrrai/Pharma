package com.example.pharmamanufacturer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pharmamanufacturer.core.PRODUCTS_GRAPH_ROUTE
import com.example.pharmamanufacturer.core.ROOT_GRAPH_ROUTE
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.dashboard.DashboardScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = PRODUCTS_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE
    ) {

        productsNavGraph(navController = navController)

        compoundsNavGraph(navController = navController)

        packagingNavGraph(navController = navController)

        composable(route = Screen.DashboardScreen.route) {
            DashboardScreen()
        }
    }
}
