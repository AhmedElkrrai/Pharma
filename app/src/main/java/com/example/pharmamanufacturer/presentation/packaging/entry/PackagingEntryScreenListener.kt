package com.example.pharmamanufacturer.presentation.packaging.entry

import com.example.pharmamanufacturer.presentation.packaging.entry.state.PackagingEntryTextField

interface PackagingEntryScreenListener {
    fun exitErrorState(textField: PackagingEntryTextField)
    fun showInvalidInput(textField: PackagingEntryTextField)
    fun addSupplier()
    fun updatePackaging()
}
