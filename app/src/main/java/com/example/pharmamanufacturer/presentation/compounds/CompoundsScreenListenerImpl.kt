package com.example.pharmamanufacturer.presentation.compounds

import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.data.local.entities.Compound

class CompoundsScreenListenerImpl(private val navController: NavHostController) :
    CompoundsScreenListener {
    override fun onCompoundClick(compound: Compound) {
        navController.navigate(
            Screen.CompoundDetailsScreen.withArgs(compound.toString())
        )
    }

    override fun onAddClick() {
        navController.navigate(
            Screen.AddCompoundScreen.route
        )
    }
}
