package com.example.pharmamanufacturer.presentation.products.main

import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen

class ProductsScreenListenerImpl(
    private val navController: NavHostController
) : ProductsScreenListener {
    override fun onProductClick(productId: String) {
        navController.navigate(
            Screen.ProductDetailsScreen.withArgs(productId)
        )
    }

    override fun onAddClick() {
        navController.navigate(
            Screen.AddProductScreen.route
        )
    }
}
