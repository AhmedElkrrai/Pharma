package com.example.pharmamanufacturer.presentation.packaging.details

import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen

class PackagingDetailsScreenListenerImpl(
    private val navController: NavHostController
) : PackagingDetailsScreenListener {
    override fun navigateBack() {
        navController.navigate(
            Screen.PackagingScreen.route
        )
    }

    override fun onEditClick(type: String?) {
        if (type == null)
            return

        navController.navigate(
            Screen.EditPackagingScreen.withArgs(type)
        )
    }
}
