package com.example.pharmamanufacturer.presentation.addcompound.action

import com.example.pharmamanufacturer.presentation.addcompound.state.AddCompoundTextField

internal sealed interface AddCompoundAction {
    object INSERT : AddCompoundAction
    data class KEYBOARD(val textField: AddCompoundTextField) : AddCompoundAction
    data class RetrieveInitialState(val textField: AddCompoundTextField) : AddCompoundAction
    object AddSupplier : AddCompoundAction
}
