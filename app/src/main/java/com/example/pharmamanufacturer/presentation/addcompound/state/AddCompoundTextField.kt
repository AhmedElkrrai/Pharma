package com.example.pharmamanufacturer.presentation.addcompound.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField

internal sealed interface AddCompoundTextField : TextField {
    object Name : AddCompoundTextField
    object Amount : AddCompoundTextField
    object SupplierName : AddCompoundTextField
    object Package : AddCompoundTextField
}
