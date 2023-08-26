package com.example.pharmamanufacturer.presentation.editcompound

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.addcompound.CompoundScreen
import com.example.pharmamanufacturer.presentation.addcompound.CompoundViewModel
import com.example.pharmamanufacturer.presentation.home.navigateToParent

@Composable
fun EditCompoundScreenScreenNavigation(
    selectedId: Int?,
    navController: NavHostController
) {

    val navigateBack = {
        navigateToParent(
            controller = navController,
            parentRoute = Screen.CompoundDetailsScreen.withArgs(selectedId.toString())
        )
    }

    val viewModel: CompoundViewModel =
        viewModel(
            factory = CompoundViewModel.Factory(
                selectedId = selectedId,
                navigateBack = navigateBack
            )
        )

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    CompoundScreen(
        viewState = viewState,
        listener = EditCompoundScreenListenerImpl(viewModel)
    )
}
