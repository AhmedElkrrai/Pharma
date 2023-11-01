package com.example.pharmamanufacturer.presentation.packaging.details

import com.example.pharmamanufacturer.data.local.entities.Packaging
import com.example.pharmamanufacturer.data.local.entities.Product

data class PackagingDetailsViewState(
    val packaging: Packaging?,
    val products: List<Product>,
) {
    companion object {
        val INIT: PackagingDetailsViewState by lazy {
            PackagingDetailsViewState(
                packaging = null,
                products = emptyList()
            )
        }
    }
}
