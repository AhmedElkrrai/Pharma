package com.example.pharmamanufacturer.presentation.addcomponent.action

import com.example.pharmamanufacturer.presentation.addcomponent.state.AddComponentEventState

internal sealed interface AddComponentAction {
    object INSERT : AddComponentAction
    data class KEYBOARD(val invalidInput: AddComponentEventState.InvalidInputState) : AddComponentAction
    object AddSupplier : AddComponentAction
}
