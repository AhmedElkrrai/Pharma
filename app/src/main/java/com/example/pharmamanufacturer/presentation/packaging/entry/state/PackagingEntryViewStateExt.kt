package com.example.pharmamanufacturer.presentation.packaging.entry.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldErrorEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.renderFieldTextViewState

internal fun PackagingEntryScreenViewState.renderViewState(
    textField: TextField,
    errorState: TextFieldErrorEventState
): PackagingEntryScreenViewState {
    return when (textField) {
        PackagingEntryTextField.Type -> {
            this.copy(
                type = renderFieldTextViewState(
                    this.type,
                    errorState
                )
            )
        }

        PackagingEntryTextField.Amount -> {
            this.copy(
                amount = renderFieldTextViewState(
                    this.amount,
                    errorState
                )
            )
        }

        PackagingEntryTextField.SupplierName -> {
            this.copy(
                supplierName = renderFieldTextViewState(
                    this.supplierName,
                    errorState
                )
            )
        }

        PackagingEntryTextField.Package -> {
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
