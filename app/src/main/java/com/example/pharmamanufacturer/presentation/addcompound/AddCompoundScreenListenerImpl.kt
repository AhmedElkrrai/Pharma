package com.example.pharmamanufacturer.presentation.addcompound

import com.example.pharmamanufacturer.presentation.addcompound.action.CompoundAction
import com.example.pharmamanufacturer.presentation.addcompound.state.CompoundTextField

class AddCompoundScreenListenerImpl(private val viewModel: CompoundViewModel) :
    UpdateCompoundScreenListener {
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
        viewModel.sendAction(CompoundAction.INSERT)
    }
}
