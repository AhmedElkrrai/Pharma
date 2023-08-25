package com.example.pharmamanufacturer.presentation.products

import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.data.local.entities.Product

class ProductsScreenListenerImpl(private val navController: NavHostController) :
    ProductsScreenListener {
    override fun onProductClick(product: Product) {
        navController.navigate(
            Screen.ProductDetailsScreen.withArgs(product.id.toString())
        )
    }

    override fun onAddClick() {
        navController.navigate(
            Screen.AddProductScreen.route
        )
    }
}
