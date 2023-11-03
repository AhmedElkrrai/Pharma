package com.example.pharmamanufacturer.presentation.products.main

import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.data.local.entities.Product

interface ProductsScreenListener {
    fun onProductClick(productId: String)
    fun onAddClick()
    fun showProductionDialog(product: Product)
    fun dismissProductionDialog(navController: NavHostController)
}
