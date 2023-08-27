package com.example.pharmamanufacturer.presentation.productentry

import com.example.pharmamanufacturer.presentation.productentry.action.ProductAction
import com.example.pharmamanufacturer.presentation.productentry.state.ProductTextField

class AddProductScreenListenerImpl(private val viewModel: ProductViewModel) :
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
        viewModel.sendAction(ProductAction.Compound)
    }

    override fun addProduct() {
        viewModel.sendAction(ProductAction.INSERT)
    }
}