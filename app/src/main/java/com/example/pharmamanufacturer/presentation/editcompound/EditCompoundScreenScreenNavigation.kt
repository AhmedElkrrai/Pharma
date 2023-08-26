package com.example.pharmamanufacturer.presentation.editcompound

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.addcompound.CompoundScreen
import com.example.pharmamanufacturer.presentation.addcompound.CompoundViewModel
import com.example.pharmamanufacturer.presentation.home.navigateToParent

@Composable
fun EditCompoundScreenScreenNavigation(navController: NavHostController) {

    val navigateBack = {
        navigateToParent(
            controller = navController,
            parentRoute = Screen.CompoundDetailsScreen.route
        )
    }

    val viewModel: CompoundViewModel =
        viewModel(
            factory = CompoundViewModel.Factory(
                navigateBack = navigateBack,
                savedStateHandle = SavedStateHandle()
            )
        )

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    CompoundScreen(
        viewState = viewState,
        listener = EditCompoundScreenListenerImpl(viewModel)
    )
}
