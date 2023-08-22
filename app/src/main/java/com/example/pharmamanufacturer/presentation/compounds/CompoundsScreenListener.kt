package com.example.pharmamanufacturer.presentation.compounds

import com.example.pharmamanufacturer.data.local.entities.Compound

interface CompoundsScreenListener {
    fun onCompoundClick(compound: Compound)
    fun onAddClick()
}
