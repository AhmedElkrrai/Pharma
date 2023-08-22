package com.example.pharmamanufacturer.presentation.addcompound

import com.example.pharmamanufacturer.presentation.addcompound.state.AddCompoundTextField

interface AddCompoundScreenListener {
    fun exitErrorState(textField: AddCompoundTextField)
    fun showInvalidInput(textField: AddCompoundTextField)
    fun addSupplier()
    fun addCompound()
}
