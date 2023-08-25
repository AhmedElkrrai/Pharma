package com.example.pharmamanufacturer.presentation.addcompound.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldErrorEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.renderFieldTextViewState

internal fun AddCompoundScreenViewState.renderViewState(
    textField: TextField,
    errorState: TextFieldErrorEventState
): AddCompoundScreenViewState {
    return when (textField) {
        AddCompoundTextField.Name -> {
            this.copy(
                name = renderFieldTextViewState(
                    this.name,
                    errorState
                )
            )
        }

        AddCompoundTextField.Amount -> {
            this.copy(
                amount = renderFieldTextViewState(
                    this.amount,
                    errorState
                )
            )
        }

        AddCompoundTextField.SupplierName -> {
            this.copy(
                supplierName = renderFieldTextViewState(
                    this.supplierName,
                    errorState
                )
            )
        }

        AddCompoundTextField.Package -> {
            this.copy(
                `package` = renderFieldTextViewState(
                    this.`package`,
                    errorState
                )
            )
        }

        else -> this
    }
}