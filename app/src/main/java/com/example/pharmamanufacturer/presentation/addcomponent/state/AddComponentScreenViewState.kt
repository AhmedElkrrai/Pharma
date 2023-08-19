package com.example.pharmamanufacturer.presentation.addcomponent.state

data class AddComponentScreenViewState(
    val name: FieldTextViewState,
    val amount: FieldTextViewState,
    val supplierName: FieldTextViewState,
    val `package`: FieldTextViewState
) {
    companion object {
        val INIT: AddComponentScreenViewState by lazy {
            AddComponentScreenViewState(
                name = FieldTextViewState.initState("Name"),
                amount = FieldTextViewState.initState("Available"),
                supplierName = FieldTextViewState.initState("Name"),
                `package` = FieldTextViewState.initState("Package")
            )
        }
    }
}
