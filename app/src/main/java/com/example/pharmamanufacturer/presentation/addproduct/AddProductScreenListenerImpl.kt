package com.example.pharmamanufacturer.presentation.addproduct

import com.example.pharmamanufacturer.presentation.addproduct.action.AddProductAction
import com.example.pharmamanufacturer.presentation.addproduct.state.AddProductTextField

class AddProductScreenListenerImpl(private val viewModel: AddProductViewModel) :
    AddProductScreenListener {

    override fun exitErrorState(textField: AddProductTextField) {
        viewModel.sendAction(
            AddProductAction.RetrieveInitialState(textField)
        )
    }

    override fun showInvalidInput(textField: AddProductTextField) {
        viewModel.sendAction(
            AddProductAction.KEYBOARD(textField)
        )
    }

    override fun addIngredient() {
        viewModel.sendAction(AddProductAction.AddIngredient)
    }

    override fun addProduct() {
        viewModel.sendAction(AddProductAction.INSERT)
    }
}