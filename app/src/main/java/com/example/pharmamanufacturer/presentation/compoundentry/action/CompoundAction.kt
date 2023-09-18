package com.example.pharmamanufacturer.presentation.compoundentry.action

import com.example.pharmamanufacturer.presentation.compoundentry.state.CompoundTextField

internal sealed interface CompoundAction {
    data class INSERT(val navigateBack: () -> Unit) : CompoundAction
    data class UPDATE(val navigateBack: () -> Unit) : CompoundAction
    data class KEYBOARD(val textField: CompoundTextField) : CompoundAction
    data class RetrieveInitialState(val textField: CompoundTextField) : CompoundAction
    object AddSupplier : CompoundAction
}
