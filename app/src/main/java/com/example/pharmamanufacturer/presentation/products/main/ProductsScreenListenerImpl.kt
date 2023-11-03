package com.example.pharmamanufacturer.presentation.products.main

import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.products.dialog.ProductionDialogAction

class ProductsScreenListenerImpl(
    private val navController: NavHostController,
    private val viewModel: ProductsViewModel
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

    override fun showProductionDialog(product: Product) {
        viewModel.sendAction(
            ProductionDialogAction.Display.SHOW(product)
        )
    }

    override fun dismissProductionDialog() {
        viewModel.sendAction(
            ProductionDialogAction.Display.DISMISS {
                navController.navigate(
                    Screen.DashboardScreen.route
                )
            }
        )
    }
}
