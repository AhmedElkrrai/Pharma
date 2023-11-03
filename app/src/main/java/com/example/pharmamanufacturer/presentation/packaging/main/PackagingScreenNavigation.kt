package com.example.pharmamanufacturer.presentation.packaging.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun PackagingScreenNavigation(
    navController: NavHostController
) {
    val viewModel: PackagingViewModel = hiltViewModel()
    val packagingList by viewModel.packagingState.collectAsStateWithLifecycle()
    val listener = PackagingScreenListenerImpl(navController)

    PackagingScreen(
        packagingList = packagingList,
        listener = listener
    )
}