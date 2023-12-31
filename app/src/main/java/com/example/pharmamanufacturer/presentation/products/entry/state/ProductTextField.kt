package com.example.pharmamanufacturer.presentation.products.entry.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField

sealed interface ProductTextField : TextField {
    object Name : ProductTextField
    object CompoundName : ProductTextField
    object Concentration : ProductTextField
    object PackagingType : ProductTextField
    object PackagingAmount : ProductTextField
}
