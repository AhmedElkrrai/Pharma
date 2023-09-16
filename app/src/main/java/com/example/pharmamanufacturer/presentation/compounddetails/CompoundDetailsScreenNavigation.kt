package com.example.pharmamanufacturer.presentation.compounddetails

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun CompoundDetailsScreenNavigation(navController: NavHostController) {

    val viewModel: CompoundDetailsViewModel = hiltViewModel()

    CompoundDetailsScreen(
        compoundState = viewModel.compoundState.collectAsStateWithLifecycle(),
        productsState = viewModel.productsState.collectAsStateWithLifecycle(),
        listener = CompoundDetailsScreenListenerImpl(navController)
    )
}
