package com.example.pharmamanufacturer.presentation.products.entry.action

import com.example.pharmamanufacturer.presentation.products.entry.state.ProductTextField

internal sealed interface ProductAction {
    data class INSERT(val navigateBack: () -> Unit) : ProductAction
    data class UPDATE(val navigateBack: () -> Unit) : ProductAction
    data class KEYBOARD(val textField: ProductTextField) : ProductAction
    data class RetrieveInitialState(val textField: ProductTextField) : ProductAction
    object COMPOUND : ProductAction
    object PACKAGING : ProductAction
}
