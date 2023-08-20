package com.example.pharmamanufacturer.presentation.addproduct.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState

data class AddProductScreenViewState(
    val name: TextFieldViewState,
    val compoundName: TextFieldViewState,
    val concentration: TextFieldViewState,
) {
    companion object {
        val INIT: AddProductScreenViewState by lazy {
            AddProductScreenViewState(
                name = TextFieldViewState.initState("Name"),
                compoundName = TextFieldViewState.initState("Name"),
                concentration = TextFieldViewState.initState("Amount")
            )
        }
    }
}
