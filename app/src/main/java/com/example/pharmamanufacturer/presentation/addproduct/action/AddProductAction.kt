package com.example.pharmamanufacturer.presentation.addproduct.action

import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.presentation.addcompound.state.AddCompoundTextField

internal sealed interface AddProductAction {
    object INSERT : AddProductAction
    data class KEYBOARD(val textField: AddCompoundTextField) : AddProductAction
    data class RetrieveInitialState(val textField: AddCompoundTextField) : AddProductAction
    data class AddCompound(val compound: Compound) : AddProductAction
}
