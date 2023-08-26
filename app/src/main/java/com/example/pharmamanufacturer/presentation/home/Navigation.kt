package com.example.pharmamanufacturer.presentation.home

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
import com.example.pharmamanufacturer.presentation.compounds.CompoundsScreen
import com.example.pharmamanufacturer.presentation.packaging.PackagingScreen
import com.example.pharmamanufacturer.presentation.products.ProductsScreen
import androidx.navigation.NavType
import com.example.pharmamanufacturer.core.Screen.Companion.EDIT_COMPOUND_ID_KEY
import com.example.pharmamanufacturer.presentation.addcompound.AddCompoundScreenNavigation
import com.example.pharmamanufacturer.presentation.addproduct.AddProductScreenNavigation
import com.example.pharmamanufacturer.presentation.compounddetails.CompoundDetailsScreenNavigation
import com.example.pharmamanufacturer.presentation.compounds.CompoundsScreenListenerImpl
import com.example.pharmamanufacturer.presentation.editcompound.EditCompoundScreenScreenNavigation
import com.example.pharmamanufacturer.presentation.productdetails.ProductDetailsScreenNavigation
import com.example.pharmamanufacturer.presentation.products.ProductsScreenListenerImpl

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

        composable(route = Screen.PackagingScreen.route) {
            PackagingScreen()
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
            route = Screen.EditCompoundScreen.route + "/{$EDIT_COMPOUND_ID_KEY}",
            arguments = listOf(
                navArgument(EDIT_COMPOUND_ID_KEY) {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { entry ->
            EditCompoundScreenScreenNavigation(
                selectedId = entry.arguments?.getInt(EDIT_COMPOUND_ID_KEY),
                navController = navController
            )
        }
    }
}
