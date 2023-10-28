package com.example.pharmamanufacturer.presentation.productdetails

import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Packaging
import com.example.pharmamanufacturer.data.local.entities.Product

data class ProductDetailsViewState(
    val product: Product?,
    val compounds: List<Compound>,
    val packagingList: List<Packaging>,
    val selectedTab: ProductDetailsTab
) {
    companion object {
        val INIT: ProductDetailsViewState by lazy {
            ProductDetailsViewState(
                product = null,
                compounds = emptyList(),
                packagingList = emptyList(),
                selectedTab = ProductDetailsTab.COMPOUNDS
            )
        }
    }
}
