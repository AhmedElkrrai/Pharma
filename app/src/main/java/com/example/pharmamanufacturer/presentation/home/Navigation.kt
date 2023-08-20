package com.example.pharmamanufacturer.presentation.home

import androidx.compose.runtime.Composable
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
import com.example.pharmamanufacturer.presentation.addcompound.AddCompoundScreen
import com.example.pharmamanufacturer.presentation.compounddetails.CompoundDetailsScreen
import com.example.pharmamanufacturer.presentation.compounds.CompoundsScreen
import com.example.pharmamanufacturer.presentation.packaging.PackagingScreen
import com.example.pharmamanufacturer.presentation.productdetails.ProductDetailsScreen
import com.example.pharmamanufacturer.presentation.products.ProductsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductsScreen.route
    ) {
        composable(route = Screen.ProductsScreen.route) {
            ProductsScreen(
                onItemClick = { product ->
                    navController.navigate(
                        Screen.ProductDetailsScreen.withArgs(product.toString())
                    )
                },
                onAddClick = {
                    navController.navigate(
                        Screen.AddProductScreen.route
                    )
                }
            )
        }
        composable(route = Screen.CompoundsScreen.route) {
            CompoundsScreen(
                onItemClick = { compound ->
                    navController.navigate(
                        Screen.CompoundDetailsScreen.withArgs(compound.toString())
                    )
                },
                onAddClick = {
                    navController.navigate(
                        Screen.AddCompoundScreen.route
                    )
                }
            )
        }
        composable(route = Screen.PackagingScreen.route) {
            PackagingScreen()
        }

        composable(
            route = Screen.ProductDetailsScreen.route + "/{$PRODUCT_DETAILS_KEY}",
            arguments = listOf(
                navArgument(PRODUCT_DETAILS_KEY) {
                    type = ProductType
                    nullable = false
                }
            )
        ) {
            ProductDetailsScreen {
                navigateToParent(
                    controller = navController,
                    parentRoute = Screen.ProductsScreen.route
                )
            }
        }

        composable(route = Screen.AddProductScreen.route) {
            AddProductScreen {
                navigateToParent(
                    controller = navController,
                    parentRoute = Screen.ProductsScreen.route
                )
            }
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
            AddCompoundScreen {
                navigateToParent(
                    controller = navController,
                    parentRoute = Screen.CompoundsScreen.route
                )
            }
        }
    }
}
