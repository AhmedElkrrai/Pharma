package com.example.pharmamanufacturer.presentation.compounddetails

interface CompoundDetailsScreenListener {
    fun navigateBack()
    fun onEditClick(compoundId: String, compoundName: String)
}
