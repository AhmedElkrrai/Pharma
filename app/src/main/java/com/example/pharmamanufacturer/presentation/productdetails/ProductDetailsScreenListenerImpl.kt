package com.example.pharmamanufacturer.presentation.productdetails

import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.home.navigateToParent

class ProductDetailsScreenListenerImpl(private val navController: NavHostController) :
    ProductDetailsScreenListener {

    override fun navigateBack() {
        navigateToParent(
            controller = navController,
            parentRoute = Screen.ProductsScreen.route
        )
    }
}
