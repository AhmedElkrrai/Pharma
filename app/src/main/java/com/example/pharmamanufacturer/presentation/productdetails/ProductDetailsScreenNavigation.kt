package com.example.pharmamanufacturer.presentation.productdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun ProductDetailsScreenNavigation(navController: NavHostController) {

    val viewModel: ProductDetailsViewModel = viewModel()

    val product by viewModel.productState.collectAsStateWithLifecycle()

    val compounds by viewModel.compoundsState.collectAsStateWithLifecycle()

    product?.let {
        ProductDetailsScreen(
            product = product!!,
            compounds = compounds,
            listener = ProductDetailsScreenListenerImpl(navController)
        )
    }
}
