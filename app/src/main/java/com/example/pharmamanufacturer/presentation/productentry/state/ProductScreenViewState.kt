package com.example.pharmamanufacturer.presentation.productentry.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState

data class ProductScreenViewState(
    val name: TextFieldViewState,
    val compoundName: TextFieldViewState,
    val concentration: TextFieldViewState,
) {
    companion object {
        val INIT: ProductScreenViewState by lazy {
            ProductScreenViewState(
                name = TextFieldViewState.initState("Name"),
                compoundName = TextFieldViewState.initState("Name"),
                concentration = TextFieldViewState.initState("Amount")
            )
        }
    }
}
