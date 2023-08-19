package com.example.pharmamanufacturer.presentation.addcomponent.state

import androidx.compose.ui.graphics.Color
import com.example.pharmamanufacturer.presentation.addcomponent.action.TextField
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.theme.Red

fun AddComponentScreenViewState.renderViewState(
    textField: TextField,
    textFieldErrorState: FieldTextErrorEventState
): AddComponentScreenViewState {
    return when (textField) {
        TextField.Name -> {
            this.copy(
                name = renderFieldTextViewState(
                    this.name,
                    textFieldErrorState
                )
            )
        }

        TextField.Amount -> {
            this.copy(
                amount = renderFieldTextViewState(
                    this.amount,
                    textFieldErrorState
                )
            )
        }

        TextField.SupplierName -> {
            this.copy(
                supplierName = renderFieldTextViewState(
                    this.supplierName,
                    textFieldErrorState
                )
            )
        }

        TextField.Capacity -> {
            this.copy(
                capacity = renderFieldTextViewState(
                    this.capacity,
                    textFieldErrorState
                )
            )
        }
    }
}

private fun renderFieldTextViewState(
    fieldTextViewState: FieldTextViewState,
    eventState: FieldTextErrorEventState
): FieldTextViewState {
    return when (eventState) {
        FieldTextErrorEventState.ENTER -> fieldTextViewState.enterErrorState()
        FieldTextErrorEventState.EXIT -> fieldTextViewState.exitErrorState()
    }
}

fun FieldTextViewState.enterErrorState(): FieldTextViewState {
    return this.copy(
        hint = FieldTextViewState.INVALID_INPUT_HINT,
        labelColor = Red,
        focusedBorderColor = Red,
        unfocusedBorderColor = Red
    )
}

fun FieldTextViewState.exitErrorState(): FieldTextViewState {
    return this.copy(
        hint = "",
        labelColor = Blue,
        focusedBorderColor = Blue,
        unfocusedBorderColor = Color.LightGray
    )
}
