package com.example.pharmamanufacturer.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.pharmamanufacturer.core.PRODUCTS_GRAPH_ROUTE
import com.example.pharmamanufacturer.core.PRODUCT_ID_KEY
import com.example.pharmamanufacturer.core.PRODUCT_NAME_KEY
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.products.details.ProductDetailsScreenNavigation
import com.example.pharmamanufacturer.presentation.products.entry.AddProductScreenNavigation
import com.example.pharmamanufacturer.presentation.products.entry.EditProductScreenNavigation
import com.example.pharmamanufacturer.presentation.products.main.ProductsScreen
import com.example.pharmamanufacturer.presentation.products.main.ProductsScreenListenerImpl

fun NavGraphBuilder.productsNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.ProductsScreen.route,
        route = PRODUCTS_GRAPH_ROUTE
    ) {

        composable(route = Screen.ProductsScreen.route) {
            ProductsScreen(navController)
        }

        composable(route = Screen.AddProductScreen.route) {
            AddProductScreenNavigation(navController)
        }

        composable(
            route = Screen.EditProductScreen.route + "/{${PRODUCT_ID_KEY}}" + "/{${PRODUCT_NAME_KEY}}",
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
            route = Screen.ProductDetailsScreen.route + "/{${PRODUCT_ID_KEY}}",
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
    }
}
