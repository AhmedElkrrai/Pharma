package com.example.pharmamanufacturer.presentation.addproduct.action

import com.example.pharmamanufacturer.presentation.addproduct.state.AddProductTextField

internal sealed interface AddProductAction {
    object INSERT : AddProductAction
    data class KEYBOARD(val textField: AddProductTextField) : AddProductAction
    data class RetrieveInitialState(val textField: AddProductTextField) : AddProductAction
    object AddIngredient : AddProductAction
}
