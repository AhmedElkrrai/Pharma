package com.example.pharmamanufacturer.presentation.packaging.entry.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState

data class PackagingEntryScreenViewState(
    val type: TextFieldViewState,
    val amount: TextFieldViewState,
    val supplierName: TextFieldViewState,
    val `package`: TextFieldViewState
) {
    companion object {
        val INIT: PackagingEntryScreenViewState by lazy {
            PackagingEntryScreenViewState(
                type = TextFieldViewState.initState("Type"),
                amount = TextFieldViewState.initState("Available"),
                supplierName = TextFieldViewState.initState("Name"),
                `package` = TextFieldViewState.initState("Package")
            )
        }
    }
}
