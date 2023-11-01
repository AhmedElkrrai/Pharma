package com.example.pharmamanufacturer.presentation.packaging.main

import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen

class PackagingScreenListenerImpl(
    private val navController: NavHostController
) : PackagingScreenListener {
    override fun onPackagingClick(packagingType: String) {
        navController.navigate(
            Screen.PackagingDetailsScreen.withArgs(packagingType)
        )
    }
}
