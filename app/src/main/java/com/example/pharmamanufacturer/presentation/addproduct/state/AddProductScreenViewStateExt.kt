package com.example.pharmamanufacturer.presentation.addproduct.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldErrorEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.renderFieldTextViewState

fun AddProductScreenViewState.renderViewState(
    textField: TextField,
    errorState: TextFieldErrorEventState
): AddProductScreenViewState {
    return when (textField) {
        AddProductTextField.Name -> {
            this.copy(
                name = renderFieldTextViewState(
                    this.name,
                    errorState
                )
            )
        }

        AddProductTextField.CompoundName -> {
            this.copy(
                compoundName = renderFieldTextViewState(
                    this.compoundName,
                    errorState
                )
            )
        }

        AddProductTextField.Concentration -> {
            this.copy(
                concentration = renderFieldTextViewState(
                    this.concentration,
                    errorState
                )
            )
        }

        else -> this
    }
}