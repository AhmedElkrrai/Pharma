package com.example.pharmamanufacturer.presentation.compounddetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.core.Screen.Companion.COMPOUND_DETAILS_KEY

class CompoundDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val selectedCompound = savedStateHandle.get<Compound>(COMPOUND_DETAILS_KEY)
}
