package com.example.pharmamanufacturer.presentation.addcomponent.state

internal sealed interface AddComponentEventState {
    object FieldValueChanged : AddComponentEventState
    object InvalidInput : AddComponentEventState
}
