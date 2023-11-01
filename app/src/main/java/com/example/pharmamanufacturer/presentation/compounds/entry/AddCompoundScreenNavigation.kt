package com.example.pharmamanufacturer.presentation.compounds.entry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.navigation.navigateToParent

@Composable
fun AddCompoundScreenNavigation(navController: NavHostController) {
    val navigateBack = {
        navigateToParent(
            controller = navController,
            parentRoute = Screen.CompoundsScreen.route
        )
    }

    val viewModel: CompoundViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    CompoundScreen(
        viewState = viewState,
        listener = AddCompoundScreenListenerImpl(viewModel, navigateBack)
    )
}
