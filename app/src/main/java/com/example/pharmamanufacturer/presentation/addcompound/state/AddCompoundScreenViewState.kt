package com.example.pharmamanufacturer.presentation.addcompound.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState

internal data class AddCompoundScreenViewState(
    val name: TextFieldViewState,
    val amount: TextFieldViewState,
    val supplierName: TextFieldViewState,
    val `package`: TextFieldViewState
) {
    companion object {
        val INIT: AddCompoundScreenViewState by lazy {
            AddCompoundScreenViewState(
                name = TextFieldViewState.initState("Name"),
                amount = TextFieldViewState.initState("Available"),
                supplierName = TextFieldViewState.initState("Name"),
                `package` = TextFieldViewState.initState("Package")
            )
        }
    }
}
