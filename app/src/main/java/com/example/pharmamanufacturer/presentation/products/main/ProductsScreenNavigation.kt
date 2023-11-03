package com.example.pharmamanufacturer.presentation.products.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun ProductsScreenNavigation(
    navController: NavHostController
) {
    val viewModel: ProductsViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val listener = ProductsScreenListenerImpl(navController, viewModel)

    ProductsScreen(
        viewState = viewState,
        listener = listener
    )
}