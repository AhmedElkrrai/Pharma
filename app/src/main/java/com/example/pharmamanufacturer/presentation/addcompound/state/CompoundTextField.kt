package com.example.pharmamanufacturer.presentation.addcompound.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField

sealed interface CompoundTextField : TextField {
    object Name : CompoundTextField
    object Amount : CompoundTextField
    object SupplierName : CompoundTextField
    object Package : CompoundTextField
}
