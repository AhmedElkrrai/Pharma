package com.example.pharmamanufacturer.presentation.packaging.entry

import com.example.pharmamanufacturer.presentation.packaging.entry.action.PackagingAction
import com.example.pharmamanufacturer.presentation.packaging.entry.state.PackagingEntryTextField

class PackagingEntryScreenListenerImpl(
    private val viewModel: PackagingEntryViewModel,
    private val navigateBack: () -> Unit
) : PackagingEntryScreenListener {
    override fun exitErrorState(textField: PackagingEntryTextField) {
        viewModel.sendAction(
            PackagingAction.RetrieveInitialState(textField)
        )
    }

    override fun showInvalidInput(textField: PackagingEntryTextField) {
        viewModel.sendAction(
            PackagingAction.KEYBOARD(textField)
        )
    }

    override fun addSupplier() {
        viewModel.sendAction(PackagingAction.AddSupplier)
    }

    override fun updatePackaging() {
        viewModel.sendAction(PackagingAction.UPDATE(navigateBack))
    }
}
