package com.example.pharmamanufacturer.presentation.addproduct.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField

sealed interface AddProductTextField : TextField {
    object Name : AddProductTextField

    object CompoundName : AddProductTextField
    object Concentration : AddProductTextField
}
