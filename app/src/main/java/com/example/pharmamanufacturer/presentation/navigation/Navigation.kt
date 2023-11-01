package com.example.pharmamanufacturer.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.core.Screen.Companion.COMPOUND_ID_KEY
import com.example.pharmamanufacturer.core.Screen.Companion.PRODUCT_ID_KEY
import com.example.pharmamanufacturer.presentation.compounds.main.CompoundsScreen
import com.example.pharmamanufacturer.presentation.packaging.main.PackagingScreen
import com.example.pharmamanufacturer.presentation.products.main.ProductsScreen
import androidx.navigation.NavType
import com.example.pharmamanufacturer.core.Screen.Companion.COMPOUND_NAME_KEY
import com.example.pharmamanufacturer.core.Screen.Companion.PACKAGING_TYPE_KEY
import com.example.pharmamanufacturer.core.Screen.Companion.PRODUCT_NAME_KEY
import com.example.pharmamanufacturer.presentation.compounds.entry.AddCompoundScreenNavigation
import com.example.pharmamanufacturer.presentation.products.entry.AddProductScreenNavigation
import com.example.pharmamanufacturer.presentation.compounds.details.CompoundDetailsScreenNavigation
import com.example.pharmamanufacturer.presentation.compounds.main.CompoundsScreenListenerImpl
import com.example.pharmamanufacturer.presentation.compounds.entry.EditCompoundScreenNavigation
import com.example.pharmamanufacturer.presentation.dashboard.DashboardScreen
import com.example.pharmamanufacturer.presentation.packaging.details.PackagingDetailsScreenNavigation
import com.example.pharmamanufacturer.presentation.packaging.main.PackagingScreenListenerImpl
import com.example.pharmamanufacturer.presentation.products.details.ProductDetailsScreenNavigation
import com.example.pharmamanufacturer.presentation.products.entry.EditProductScreenNavigation
import com.example.pharmamanufacturer.presentation.products.main.ProductsScreenListenerImpl

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductsScreen.route
    ) {

        composable(route = Screen.ProductsScreen.route) {
            ProductsScreen(
                ProductsScreenListenerImpl(navController)
            )
        }

        composable(route = Screen.CompoundsScreen.route) {
            CompoundsScreen(
                CompoundsScreenListenerImpl(navController)
            )
        }

        composable(route = Screen.DashboardScreen.route) {
            DashboardScreen()
        }

        composable(route = Screen.PackagingScreen.route) {
            PackagingScreen(
                PackagingScreenListenerImpl(navController)
            )
        }

        composable(
            route = Screen.ProductDetailsScreen.route + "/{$PRODUCT_ID_KEY}",
            arguments = listOf(
                navArgument(PRODUCT_ID_KEY) {
                    type = NavType.IntType
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
            ProductDetailsScreenNavigation(navController)
        }

        composable(route = Screen.AddProductScreen.route) {
            AddProductScreenNavigation(navController)
        }

        composable(
            route = Screen.EditProductScreen.route + "/{$PRODUCT_ID_KEY}" + "/{$PRODUCT_NAME_KEY}",
            arguments = listOf(
                navArgument(PRODUCT_ID_KEY) {
                    type = NavType.IntType
                    nullable = false
                },
                navArgument(PRODUCT_NAME_KEY) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            EditProductScreenNavigation(
                selectedId = entry.arguments?.getInt(PRODUCT_ID_KEY),
                navController = navController
            )
        }

        composable(
            route = Screen.CompoundDetailsScreen.route + "/{$COMPOUND_ID_KEY}",
            arguments = listOf(
                navArgument(COMPOUND_ID_KEY) {
                    type = NavType.IntType
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
            CompoundDetailsScreenNavigation(navController)
        }

        composable(route = Screen.AddCompoundScreen.route) {
            AddCompoundScreenNavigation(navController)
        }

        composable(
            route = Screen.EditCompoundScreen.route + "/{$COMPOUND_ID_KEY}" + "/{$COMPOUND_NAME_KEY}",
            arguments = listOf(
                navArgument(COMPOUND_ID_KEY) {
                    type = NavType.IntType
                    nullable = false
                },
                navArgument(COMPOUND_NAME_KEY) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            EditCompoundScreenNavigation(
                selectedId = entry.arguments?.getInt(COMPOUND_ID_KEY),
                navController = navController
            )
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
    }
}
