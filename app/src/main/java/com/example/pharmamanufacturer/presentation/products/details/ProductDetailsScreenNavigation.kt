package com.example.pharmamanufacturer.presentation.products.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun ProductDetailsScreenNavigation(navController: NavHostController) {

    val viewModel: ProductDetailsViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    ProductDetailsScreen(
        viewState = viewState,
        listener = ProductDetailsScreenListenerImpl(viewModel, navController)
    )
}
