package com.example.pharmamanufacturer.presentation.utilitycompose.textfield

sealed interface TextFieldEventState {
    data class InvalidInput(val textField: TextField) : TextFieldEventState
}
