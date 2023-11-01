package com.example.pharmamanufacturer.presentation.packaging.entry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.navigation.navigateToParent

@Composable
fun EditPackagingNavigation(
    packagingType: String?,
    navController: NavHostController
) {
    if (packagingType == null)
        return

    val navigateBack = {
        navigateToParent(
            controller = navController,
            parentRoute = Screen.PackagingDetailsScreen.withArgs(packagingType)
        )
    }

    val viewModel: PackagingEntryViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    PackagingEntryScreen(
        viewState = viewState,
        listener = PackagingEntryScreenListenerImpl(viewModel, navigateBack)
    )
}