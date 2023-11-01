package com.example.pharmamanufacturer.presentation.products.entry.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState

data class ProductScreenViewState(
    val name: TextFieldViewState,
    val compoundName: TextFieldViewState,
    val concentration: TextFieldViewState,
    val packagingType: TextFieldViewState,
    val packagingAmount: TextFieldViewState,
) {
    companion object {
        val INIT: ProductScreenViewState by lazy {
            ProductScreenViewState(
                name = TextFieldViewState.initState("Name"),
                compoundName = TextFieldViewState.initState("Name"),
                concentration = TextFieldViewState.initState("Amount"),
                packagingType = TextFieldViewState.initState("Type"),
                packagingAmount = TextFieldViewState.initState("Amount")
            )
        }
    }
}
