package com.example.pharmamanufacturer.presentation.addcompound

import com.example.pharmamanufacturer.presentation.addcompound.state.CompoundTextField

interface UpdateCompoundScreenListener {
    fun exitErrorState(textField: CompoundTextField)
    fun showInvalidInput(textField: CompoundTextField)
    fun addSupplier()
    fun addCompound()
}
