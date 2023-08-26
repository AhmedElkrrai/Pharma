package com.example.pharmamanufacturer.presentation.editcompound

import com.example.pharmamanufacturer.presentation.addcompound.CompoundViewModel
import com.example.pharmamanufacturer.presentation.addcompound.UpdateCompoundScreenListener
import com.example.pharmamanufacturer.presentation.addcompound.action.CompoundAction
import com.example.pharmamanufacturer.presentation.addcompound.state.CompoundTextField

class EditCompoundScreenListenerImpl(private val viewModel: CompoundViewModel) :
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
        viewModel.sendAction(CompoundAction.UPDATE)
    }
}