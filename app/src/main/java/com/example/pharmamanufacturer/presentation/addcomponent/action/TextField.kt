package com.example.pharmamanufacturer.presentation.addcomponent.action

sealed interface TextField {
    object Name : TextField
    object Amount : TextField
    object SupplierName : TextField
    object Capacity : TextField
}
