package com.example.pharmamanufacturer.presentation.addcompound

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.home.navigateToParent

@Composable
fun AddCompoundScreenNavigation(navController: NavHostController) {
    val navigateBack = {
        navigateToParent(
            controller = navController,
            parentRoute = Screen.CompoundsScreen.route
        )
    }

    val viewModel: CompoundViewModel =
        viewModel(
            factory = CompoundViewModel.Factory(
                navigateBack = navigateBack
            )
        )

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    CompoundScreen(
        viewState = viewState,
        listener = AddCompoundScreenListenerImpl(viewModel)
    )
}
