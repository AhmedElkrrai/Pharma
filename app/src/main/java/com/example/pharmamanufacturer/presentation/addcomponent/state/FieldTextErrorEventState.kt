package com.example.pharmamanufacturer.presentation.addcomponent.state

sealed interface FieldTextErrorEventState {
    object ENTER : FieldTextErrorEventState
    object EXIT : FieldTextErrorEventState
}
