package com.example.pharmamanufacturer.presentation.addproduct

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

    val viewModel: AddProductViewModel =
        viewModel(factory = AddProductViewModel.Factory(navigateBack))

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    AddProductScreen(
        viewState = viewState,
        listener = AddProductScreenListenerImpl(viewModel)
    )
}
