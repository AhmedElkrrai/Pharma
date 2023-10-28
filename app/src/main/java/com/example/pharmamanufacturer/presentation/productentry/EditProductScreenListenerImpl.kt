package com.example.pharmamanufacturer.presentation.productentry

import com.example.pharmamanufacturer.presentation.productentry.action.ProductAction
import com.example.pharmamanufacturer.presentation.productentry.state.ProductTextField

class EditProductScreenListenerImpl(
    private val viewModel: ProductViewModel,
    private val navigateBack: () -> Unit
) :
    ProductScreenListener {
    override fun exitErrorState(textField: ProductTextField) {
        viewModel.sendAction(
            ProductAction.RetrieveInitialState(textField)
        )
    }

    override fun showInvalidInput(textField: ProductTextField) {
        viewModel.sendAction(
            ProductAction.KEYBOARD(textField)
        )
    }

    override fun addCompound() {
        viewModel.sendAction(ProductAction.COMPOUND)
    }

    override fun addProduct() {
        viewModel.sendAction(
            ProductAction.UPDATE(navigateBack)
        )
    }

    override fun addPackaging() {
        viewModel.sendAction(ProductAction.PACKAGING)
    }
}