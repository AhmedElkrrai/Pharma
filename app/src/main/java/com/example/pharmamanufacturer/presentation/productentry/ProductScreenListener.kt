package com.example.pharmamanufacturer.presentation.productentry

import com.example.pharmamanufacturer.presentation.productentry.state.ProductTextField

interface ProductScreenListener {
    fun exitErrorState(textField: ProductTextField)
    fun showInvalidInput(textField: ProductTextField)
    fun addCompound()
    fun addProduct()
    fun addPackaging()
}
