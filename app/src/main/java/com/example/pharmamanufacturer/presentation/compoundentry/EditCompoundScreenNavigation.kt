package com.example.pharmamanufacturer.presentation.compoundentry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.home.navigateToParent

@Composable
fun EditCompoundScreenNavigation(
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
