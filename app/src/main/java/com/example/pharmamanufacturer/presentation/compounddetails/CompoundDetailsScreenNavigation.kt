package com.example.pharmamanufacturer.presentation.compounddetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun CompoundDetailsScreenNavigation(navController: NavHostController) {

    val viewModel: CompoundDetailsViewModel = viewModel()

    val compound  by viewModel.compoundState.collectAsStateWithLifecycle()

    val products by viewModel.productsState.collectAsStateWithLifecycle()

    compound?.let {
        CompoundDetailsScreen(
            compound = compound!!,
            products = products,
            listener = CompoundDetailsScreenListenerImpl(navController)
        )
    }
}
