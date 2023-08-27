package com.example.pharmamanufacturer.presentation.compoundentry

import com.example.pharmamanufacturer.presentation.compoundentry.action.CompoundAction
import com.example.pharmamanufacturer.presentation.compoundentry.state.CompoundTextField

class EditCompoundScreenListenerImpl(private val viewModel: CompoundViewModel) :
    CompoundScreenListener {
    override fun exitErrorState(textField: CompoundTextField) {
        viewModel.sendAction(
            CompoundAction.RetrieveInitialState(textField)
        )
    }

    override fun showInvalidInput(textField: CompoundTextField) {
        viewModel.sendAction(
            CompoundAction.KEYBOARD(textField)
        )
    }

    override fun addSupplier() {
        viewModel.sendAction(CompoundAction.AddSupplier)
    }

    override fun addCompound() {
        viewModel.sendAction(CompoundAction.UPDATE)
    }
}