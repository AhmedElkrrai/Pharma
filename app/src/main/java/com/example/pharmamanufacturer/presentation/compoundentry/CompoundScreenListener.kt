package com.example.pharmamanufacturer.presentation.compoundentry

import com.example.pharmamanufacturer.presentation.compoundentry.state.CompoundTextField

interface CompoundScreenListener {
    fun exitErrorState(textField: CompoundTextField)
    fun showInvalidInput(textField: CompoundTextField)
    fun addSupplier()
    fun addCompound()
}
