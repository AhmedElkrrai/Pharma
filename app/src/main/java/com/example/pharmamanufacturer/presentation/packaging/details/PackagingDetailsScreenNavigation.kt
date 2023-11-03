package com.example.pharmamanufacturer.presentation.packaging.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun PackagingDetailsScreenNavigation(navController: NavHostController) {

    val viewModel: PackagingDetailsViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    PackagingDetailsScreen(
        viewState = viewState,
        listener = PackagingDetailsScreenListenerImpl(navController)
    )
}
