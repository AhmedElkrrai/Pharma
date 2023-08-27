package com.example.pharmamanufacturer.presentation.productentry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.home.navigateToParent

@Composable
fun EditProductScreenNavigation(
    selectedId: Int?,
    navController: NavHostController
) {
    val navigateBack = {
        navigateToParent(
            controller = navController,
            parentRoute = Screen.ProductDetailsScreen.withArgs(selectedId.toString())
        )
    }

    val viewModel: ProductViewModel =
        viewModel(
            factory = ProductViewModel.Factory(
                selectedId = selectedId,
                navigateBack = navigateBack
            )
        )

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    ProductScreen(
        viewState = viewState,
        listener = EditProductScreenListenerImpl(viewModel)
    )
}