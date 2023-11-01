package com.example.pharmamanufacturer.presentation.compounds.main

import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen

class CompoundsScreenListenerImpl(
    private val navController: NavHostController
) : CompoundsScreenListener {
    override fun onCompoundClick(compoundId: String) {
        navController.navigate(
            Screen.CompoundDetailsScreen.withArgs(compoundId)
        )
    }

    override fun onAddClick() {
        navController.navigate(
            Screen.AddCompoundScreen.route
        )
    }
}
