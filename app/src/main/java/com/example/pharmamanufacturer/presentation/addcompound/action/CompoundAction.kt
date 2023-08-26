package com.example.pharmamanufacturer.presentation.addcompound.action

import com.example.pharmamanufacturer.presentation.addcompound.state.CompoundTextField

internal sealed interface CompoundAction {
    object INSERT : CompoundAction
    object UPDATE : CompoundAction
    data class KEYBOARD(val textField: CompoundTextField) : CompoundAction
    data class RetrieveInitialState(val textField: CompoundTextField) : CompoundAction
    object AddSupplier : CompoundAction
}
