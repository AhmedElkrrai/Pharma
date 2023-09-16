package com.example.pharmamanufacturer.presentation.productdetails

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun ProductDetailsScreenNavigation(navController: NavHostController) {

    val viewModel: ProductDetailsViewModel = hiltViewModel()

    ProductDetailsScreen(
        productState = viewModel.productState.collectAsStateWithLifecycle(),
        compoundsState = viewModel.compoundsState.collectAsStateWithLifecycle(),
        listener = ProductDetailsScreenListenerImpl(navController)
    )
}
