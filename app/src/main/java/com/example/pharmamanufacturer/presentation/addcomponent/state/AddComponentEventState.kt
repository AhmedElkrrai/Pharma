package com.example.pharmamanufacturer.presentation.addcomponent.state

import com.example.pharmamanufacturer.presentation.addcomponent.action.TextField

sealed interface AddComponentEventState {
    data class FieldValueChanged(val textField: TextField) : AddComponentEventState
    data class InvalidInput(val textField: TextField) : AddComponentEventState
    object ClearSupplierInputs : AddComponentEventState
}
