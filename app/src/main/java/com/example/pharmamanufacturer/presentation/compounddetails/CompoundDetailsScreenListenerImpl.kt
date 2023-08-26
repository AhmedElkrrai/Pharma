package com.example.pharmamanufacturer.presentation.compounddetails

import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.presentation.home.navigateToParent

class CompoundDetailsScreenListenerImpl(private val navController: NavHostController) :
    CompoundDetailsScreenListener {
    override fun navigateBack() {
        navigateToParent(
            controller = navController,
            parentRoute = Screen.CompoundsScreen.route
        )
    }

    override fun onEditClick(compoundId:String) {
        navController.navigate(
            Screen.EditCompoundScreen.withArgs(compoundId)
        )
    }
}
