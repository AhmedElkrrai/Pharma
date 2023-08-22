package com.example.pharmamanufacturer.presentation.addcompound

import com.example.pharmamanufacturer.presentation.addcompound.action.AddCompoundAction
import com.example.pharmamanufacturer.presentation.addcompound.state.AddCompoundTextField

class AddCompoundScreenListenerImpl(private val viewModel: AddCompoundViewModel) :
    AddCompoundScreenListener {
    override fun exitErrorState(textField: AddCompoundTextField) {
        viewModel.sendAction(
            AddCompoundAction.RetrieveInitialState(textField)
        )
    }

    override fun showInvalidInput(textField: AddCompoundTextField) {
        viewModel.sendAction(
            AddCompoundAction.KEYBOARD(textField)
        )
    }

    override fun addSupplier() {
        viewModel.sendAction(AddCompoundAction.AddSupplier)
    }

    override fun addCompound() {
        viewModel.sendAction(AddCompoundAction.INSERT)
    }
}
