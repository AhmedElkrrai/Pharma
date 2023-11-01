package com.example.pharmamanufacturer.presentation.compounds.entry

import com.example.pharmamanufacturer.presentation.compounds.entry.action.CompoundAction
import com.example.pharmamanufacturer.presentation.compounds.entry.state.CompoundTextField

class AddCompoundScreenListenerImpl(
    private val viewModel: CompoundViewModel,
    private val navigateBack: () -> Unit
) : CompoundScreenListener {
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
        viewModel.sendAction(CompoundAction.INSERT(navigateBack))
    }
}
