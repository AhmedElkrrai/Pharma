package com.example.pharmamanufacturer.presentation.products.details

import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.PRODUCTS_GRAPH_ROUTE
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.navigation.navigateToParent

class ProductDetailsScreenListenerImpl(
    private val viewModel: ProductDetailsViewModel,
    private val navController: NavHostController
) :
    ProductDetailsScreenListener {

    override fun navigateBack() {
        navController.navigate(
            Screen.ProductsScreen.route
        )
    }

    override fun onEditClick(productId: String, productName: String) {
        navController.navigate(
            Screen.EditProductScreen.withArgs(productId, productName)
        )
    }

    override fun onTabSelected(tab: ProductDetailsTab) {
        viewModel.sendAction(ProductDetailsAction.SelectTab(tab))
    }
}
