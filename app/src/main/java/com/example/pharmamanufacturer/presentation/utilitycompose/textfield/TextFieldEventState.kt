package com.example.pharmamanufacturer.presentation.utilitycompose.textfield

import com.example.pharmamanufacturer.presentation.addcompound.state.AddCompoundTextField

sealed interface TextFieldEventState {
    data class InvalidInput(val textField: AddCompoundTextField) : TextFieldEventState
    object ClearSupplierInputs : TextFieldEventState
}
