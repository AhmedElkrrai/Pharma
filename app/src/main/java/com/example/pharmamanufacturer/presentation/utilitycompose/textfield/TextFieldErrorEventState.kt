package com.example.pharmamanufacturer.presentation.utilitycompose.textfield

sealed interface TextFieldErrorEventState {
    object ENTER : TextFieldErrorEventState
    object EXIT : TextFieldErrorEventState
}
