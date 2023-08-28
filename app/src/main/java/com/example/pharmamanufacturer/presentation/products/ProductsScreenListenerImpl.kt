package com.example.pharmamanufacturer.presentation.products

import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen

class ProductsScreenListenerImpl(private val navController: NavHostController) :
    ProductsScreenListener {
    override fun onProductClick(productId: String) {
        navController.navigate(
            Screen.ProductDetailsScreen.withArgs(productId)
        )
    }

    override fun onProductionStarted(productId: String) {
        navController.navigate(
            Screen.DashboardScreen.route
        )
    }

    override fun onAddClick() {
        navController.navigate(
            Screen.AddProductScreen.route
        )
    }
}
