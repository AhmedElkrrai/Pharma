package com.example.pharmamanufacturer.presentation.packaging.entry.state

import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField

sealed interface PackagingEntryTextField : TextField {
    object Type : PackagingEntryTextField
    object Amount : PackagingEntryTextField
    object SupplierName : PackagingEntryTextField
    object Package : PackagingEntryTextField
}