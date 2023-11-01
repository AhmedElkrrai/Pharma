package com.example.pharmamanufacturer.presentation.packaging.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.presentation.compounds.details.CompoundDetailsScreen
import com.example.pharmamanufacturer.presentation.compounds.details.CompoundDetailsScreenListenerImpl
import com.example.pharmamanufacturer.presentation.compounds.details.CompoundDetailsViewModel

@Composable
fun PackagingDetailsScreenNavigation(navController: NavHostController) {

    val viewModel: PackagingDetailsViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    PackagingDetailsScreen(
        viewState = viewState,
        listener = PackagingDetailsScreenListenerImpl(navController)
    )
}
