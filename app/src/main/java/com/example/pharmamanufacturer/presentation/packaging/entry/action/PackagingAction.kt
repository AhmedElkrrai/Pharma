package com.example.pharmamanufacturer.presentation.packaging.entry.action

import com.example.pharmamanufacturer.presentation.packaging.entry.state.PackagingEntryTextField

internal sealed interface PackagingAction {
    data class UPDATE(val navigateBack: () -> Unit) : PackagingAction
    data class KEYBOARD(val textField: PackagingEntryTextField) : PackagingAction
    data class RetrieveInitialState(val textField: PackagingEntryTextField) : PackagingAction
    object AddSupplier : PackagingAction
}