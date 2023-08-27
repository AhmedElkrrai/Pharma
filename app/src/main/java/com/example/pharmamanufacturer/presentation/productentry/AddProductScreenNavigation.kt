package com.example.pharmamanufacturer.presentation.productentry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.home.navigateToParent

@Composable
fun AddProductScreenNavigation(navController: NavHostController) {
    val navigateBack = {
        navigateToParent(
            controller = navController,
            parentRoute = Screen.ProductsScreen.route
        )
    }

    val viewModel: ProductViewModel =
        viewModel(factory = ProductViewModel.Factory(navigateBack))

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    ProductScreen(
        viewState = viewState,
        listener = AddProductScreenListenerImpl(viewModel)
    )
}
