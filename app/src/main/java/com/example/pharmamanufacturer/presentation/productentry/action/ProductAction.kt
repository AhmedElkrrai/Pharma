package com.example.pharmamanufacturer.presentation.productentry.action

import com.example.pharmamanufacturer.presentation.productentry.state.ProductTextField

internal sealed interface ProductAction {
    data class INSERT(val navigateBack: () -> Unit) : ProductAction
    data class UPDATE(val navigateBack: () -> Unit) : ProductAction
    data class KEYBOARD(val textField: ProductTextField) : ProductAction
    data class RetrieveInitialState(val textField: ProductTextField) : ProductAction
    object Compound : ProductAction
}
