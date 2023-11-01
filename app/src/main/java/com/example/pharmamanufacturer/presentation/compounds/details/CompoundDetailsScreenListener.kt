package com.example.pharmamanufacturer.presentation.compounds.details

interface CompoundDetailsScreenListener {
    fun navigateBack()
    fun onEditClick(compoundId: String, compoundName: String)
}
