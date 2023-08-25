package com.example.pharmamanufacturer.presentation.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pharmamanufacturer.core.CompoundType
import com.example.pharmamanufacturer.core.ProductType
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.core.Screen.Companion.COMPOUND_DETAILS_KEY
import com.example.pharmamanufacturer.core.Screen.Companion.PRODUCT_DETAILS_KEY
import com.example.pharmamanufacturer.presentation.addproduct.AddProductScreen
import com.example.pharmamanufacturer.presentation.addproduct.AddProductViewModel
import com.example.pharmamanufacturer.presentation.compounddetails.CompoundDetailsScreen
import com.example.pharmamanufacturer.presentation.compounds.CompoundsScreen
import com.example.pharmamanufacturer.presentation.packaging.PackagingScreen
import com.example.pharmamanufacturer.presentation.productdetails.ProductDetailsScreen
import com.example.pharmamanufacturer.presentation.products.ProductsScreen
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import com.example.pharmamanufacturer.presentation.addcompound.AddCompoundScreenNavigation
import com.example.pharmamanufacturer.presentation.addproduct.AddProductScreenListenerImpl
import com.example.pharmamanufacturer.presentation.addproduct.AddProductScreenNavigation
import com.example.pharmamanufacturer.presentation.compounds.CompoundsScreenListenerImpl
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
            route = Screen.ProductDetailsScreen.route + "/{$PRODUCT_DETAILS_KEY}",
            arguments = listOf(
                navArgument(PRODUCT_DETAILS_KEY) {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) {
            ProductDetailsScreenNavigation(navController)
        }

        composable(route = Screen.AddProductScreen.route) {
            AddProductScreenNavigation(navController)
        }

        composable(
            route = Screen.CompoundDetailsScreen.route + "/{$COMPOUND_DETAILS_KEY}",
            arguments = listOf(
                navArgument(COMPOUND_DETAILS_KEY) {
                    type = CompoundType
                    nullable = false
                }
            )
        ) {
            CompoundDetailsScreen {
                navigateToParent(
                    controller = navController,
                    parentRoute = Screen.CompoundsScreen.route
                )
            }
        }

        composable(route = Screen.AddCompoundScreen.route) {
            AddCompoundScreenNavigation(navController)
        }
    }
}
