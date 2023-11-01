package com.example.pharmamanufacturer.presentation.compounds.entry.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldErrorEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.renderFieldTextViewState

internal fun CompoundScreenViewState.renderViewState(
    textField: TextField,
    errorState: TextFieldErrorEventState
): CompoundScreenViewState {
    return when (textField) {
        CompoundTextField.Name -> {
            this.copy(
                name = renderFieldTextViewState(
                    this.name,
                    errorState
                )
            )
        }

        CompoundTextField.Amount -> {
            this.copy(
                amount = renderFieldTextViewState(
                    this.amount,
                    errorState
                )
            )
        }

        CompoundTextField.SupplierName -> {
            this.copy(
                supplierName = renderFieldTextViewState(
                    this.supplierName,
                    errorState
                )
            )
        }

        CompoundTextField.Package -> {
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
