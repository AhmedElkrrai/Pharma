package com.example.pharmamanufacturer.presentation.addcomponent.state

sealed interface AddComponentEventState {
    object FieldValueChanged : AddComponentEventState
    data class InvalidInputState(
        val name: Boolean = false,
        val amount: Boolean = false,
        val supplierName: Boolean = false,
        val capacity: Boolean = false
    ) : AddComponentEventState

    object ClearSupplierInputs : AddComponentEventState
}
