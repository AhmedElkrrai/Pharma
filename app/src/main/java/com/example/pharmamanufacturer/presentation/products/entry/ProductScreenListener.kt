package com.example.pharmamanufacturer.presentation.products.entry

import com.example.pharmamanufacturer.presentation.products.entry.state.ProductTextField

interface ProductScreenListener {
    fun exitErrorState(textField: ProductTextField)
    fun showInvalidInput(textField: ProductTextField)
    fun addCompound()
    fun addProduct()
    fun addPackaging()
}
