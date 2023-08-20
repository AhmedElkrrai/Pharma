package com.example.pharmamanufacturer.presentation.addcompound.state

sealed interface AddCompoundTextField {
    object Name : AddCompoundTextField
    object Amount : AddCompoundTextField
    object SupplierName : AddCompoundTextField
    object Package : AddCompoundTextField
}
