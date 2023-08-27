package com.example.pharmamanufacturer.presentation.productentry.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldErrorEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.renderFieldTextViewState

fun ProductScreenViewState.renderViewState(
    textField: TextField,
    errorState: TextFieldErrorEventState
): ProductScreenViewState {
    return when (textField) {
        ProductTextField.Name -> {
            this.copy(
                name = renderFieldTextViewState(
                    this.name,
                    errorState
                )
            )
        }

        ProductTextField.CompoundName -> {
            this.copy(
                compoundName = renderFieldTextViewState(
                    this.compoundName,
                    errorState
                )
            )
        }

        ProductTextField.Concentration -> {
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