package com.example.pharmamanufacturer.presentation.addcomponent.action

internal sealed interface AddComponentAction {
    object INSERT : AddComponentAction
    data class KEYBOARD(val textField: TextField) : AddComponentAction
    data class RetrieveInitialState(val textField: TextField) : AddComponentAction
    object AddSupplier : AddComponentAction
}
