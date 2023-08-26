package com.example.pharmamanufacturer.presentation.compounddetails

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun CompoundDetailsScreenNavigation(navController: NavHostController) {

    val viewModel: CompoundDetailsViewModel = viewModel()

    CompoundDetailsScreen(
        compoundState = viewModel.compoundState.collectAsStateWithLifecycle(),
        productsState = viewModel.productsState.collectAsStateWithLifecycle(),
        listener = CompoundDetailsScreenListenerImpl(navController)
    )
}
