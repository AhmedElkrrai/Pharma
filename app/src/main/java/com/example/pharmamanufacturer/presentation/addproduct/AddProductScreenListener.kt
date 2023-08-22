package com.example.pharmamanufacturer.presentation.addproduct

import com.example.pharmamanufacturer.presentation.addproduct.state.AddProductTextField

interface AddProductScreenListener {
    fun exitErrorState(textField: AddProductTextField)
    fun showInvalidInput(textField: AddProductTextField)
    fun addCompound()
    fun addProduct()
}
