package com.example.pharmamanufacturer.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.pharmamanufacturer.core.PACKAGING_GRAPH_ROUTE
import com.example.pharmamanufacturer.core.PACKAGING_TYPE_KEY
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.packaging.details.PackagingDetailsScreenNavigation
import com.example.pharmamanufacturer.presentation.packaging.entry.EditPackagingNavigation
import com.example.pharmamanufacturer.presentation.packaging.main.PackagingScreen
import com.example.pharmamanufacturer.presentation.packaging.main.PackagingScreenListenerImpl
import com.example.pharmamanufacturer.presentation.packaging.main.PackagingScreenNavigation

fun NavGraphBuilder.packagingNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.CompoundsScreen.route,
        route = PACKAGING_GRAPH_ROUTE
    ) {

        composable(route = Screen.PackagingScreen.route) {
            PackagingScreenNavigation(navController)
        }

        composable(
            route = Screen.PackagingDetailsScreen.route + "/{$PACKAGING_TYPE_KEY}",
            arguments = listOf(
                navArgument(PACKAGING_TYPE_KEY) {
                    type = NavType.StringType
                    nullable = false
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up,
                    animationSpec = tween(700)
                )
            }
        ) {
            PackagingDetailsScreenNavigation(navController)
        }

        composable(
            route = Screen.EditPackagingScreen.route + "/{$PACKAGING_TYPE_KEY}",
            arguments = listOf(
                navArgument(PACKAGING_TYPE_KEY) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            EditPackagingNavigation(
                packagingType = entry.arguments?.getString(PACKAGING_TYPE_KEY),
                navController = navController
            )
        }
    }
}
