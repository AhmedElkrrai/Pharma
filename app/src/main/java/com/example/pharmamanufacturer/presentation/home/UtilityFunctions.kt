package com.example.pharmamanufacturer.presentation.home

import androidx.navigation.NavHostController

internal fun navigateToParent(
    controller: NavHostController,
    parentRoute: String
) {
    controller.navigate(parentRoute) {
        popUpTo(parentRoute) {
            inclusive = true
        }
    }
}
