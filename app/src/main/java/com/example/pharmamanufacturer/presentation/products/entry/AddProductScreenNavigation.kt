package com.example.pharmamanufacturer.presentation.products.entry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.navigation.navigateToParent

@Composable
fun AddProductScreenNavigation(navController: NavHostController) {
    val navigateBack = {
        navigateToParent(
            controller = navController,
            parentRoute = Screen.ProductsScreen.route
        )
    }

    val viewModel: ProductViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    ProductScreen(
        viewState = viewState,
        listener = AddProductScreenListenerImpl(viewModel, navigateBack)
    )
}
