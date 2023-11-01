package com.example.pharmamanufacturer.presentation.products.entry

import com.example.pharmamanufacturer.presentation.products.entry.action.ProductAction
import com.example.pharmamanufacturer.presentation.products.entry.state.ProductTextField

class AddProductScreenListenerImpl(
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
            ProductAction.INSERT(navigateBack)
        )
    }

    override fun addPackaging() {
        viewModel.sendAction(ProductAction.PACKAGING)
    }
}