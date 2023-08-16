package com.example.pharmamanufacturer.presentation.addcomponent.action

internal sealed interface AddComponentAction {
    object INSERT : AddComponentAction
    data class KEYBOARD(val invalidInput: Boolean) : AddComponentAction
    object AddSupplier : AddComponentAction
}
