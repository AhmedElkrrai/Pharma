package com.example.pharmamanufacturer.presentation.compoundentry.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState

data class CompoundScreenViewState(
    val name: TextFieldViewState,
    val amount: TextFieldViewState,
    val supplierName: TextFieldViewState,
    val `package`: TextFieldViewState
) {
    companion object {
        val INIT: CompoundScreenViewState by lazy {
            CompoundScreenViewState(
                name = TextFieldViewState.initState("Name"),
                amount = TextFieldViewState.initState("Available"),
                supplierName = TextFieldViewState.initState("Name"),
                `package` = TextFieldViewState.initState("Package")
            )
        }
    }
}
