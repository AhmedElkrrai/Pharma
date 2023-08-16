package com.example.pharmamanufacturer.presentation.addcomponent.state

data class AddComponentScreenViewState(
    val name: FieldTextViewState,
    val amount: FieldTextViewState,
    val supplierName: FieldTextViewState,
    val capacity: FieldTextViewState
) {
    companion object {
        val INIT: AddComponentScreenViewState by lazy {
            AddComponentScreenViewState(
                name = FieldTextViewState.initState("Name"),
                amount = FieldTextViewState.initState("Available"),
                supplierName = FieldTextViewState.initState("Name"),
                capacity = FieldTextViewState.initState("Capacity")
            )
        }
    }
}
