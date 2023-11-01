package com.example.pharmamanufacturer.presentation.compounds.entry

import com.example.pharmamanufacturer.presentation.compounds.entry.state.CompoundTextField

interface CompoundScreenListener {
    fun exitErrorState(textField: CompoundTextField)
    fun showInvalidInput(textField: CompoundTextField)
    fun addSupplier()
    fun addCompound()
}
