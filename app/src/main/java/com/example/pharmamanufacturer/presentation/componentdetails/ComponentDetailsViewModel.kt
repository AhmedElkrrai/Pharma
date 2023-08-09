package com.example.pharmamanufacturer.presentation.componentdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import com.example.pharmamanufacturer.presentation.COMPONENT_DETAILS_KEY

class ComponentDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val selectedComponent = savedStateHandle.get<ChemicalComponent>(COMPONENT_DETAILS_KEY)
}
