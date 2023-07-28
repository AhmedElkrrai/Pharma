package com.example.pharmamanufacturer.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pharmamanufacturer.data.models.ChemicalComponent

class ComponentsViewModel : ViewModel() {
    var componentsState by mutableStateOf(getComponents())

    fun updateAmount(componentId: Int) {
        val components = componentsState.toMutableList()
        val componentIndex = componentsState.indexOfFirst { it.id == componentId }
        components[componentIndex] =
            components[componentIndex].copy(amount = components[componentIndex].amount + 1)
        componentsState = components
    }

    private fun getComponents(): List<ChemicalComponent> {
        return listOf(
            ChemicalComponent(1, "H2", 100.0),
            ChemicalComponent(2, "N", 40.0),
            ChemicalComponent(3, "O", 22.0),
            ChemicalComponent(4, "S", 67.0),
            ChemicalComponent(5, "Na", 98.0),
            ChemicalComponent(6, "P", 38.0),
            ChemicalComponent(7, "C", 21.0),
            ChemicalComponent(8, "L", 62.0),
            ChemicalComponent(9, "Ge", 15.0),
            ChemicalComponent(10, "Ma", 19.0),
            ChemicalComponent(11, "U", 33.0)
        )
    }
}