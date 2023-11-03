package com.example.pharmamanufacturer.presentation.compounds.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun CompoundsScreenNavigation(
    navController: NavHostController
) {
    val viewModel: CompoundsViewModel = hiltViewModel()
    val compoundsState by viewModel.compoundsState.collectAsStateWithLifecycle()
    val listener: CompoundsScreenListener = CompoundsScreenListenerImpl(navController)

    CompoundsScreen(
        compounds = compoundsState,
        listener = listener
    )
}