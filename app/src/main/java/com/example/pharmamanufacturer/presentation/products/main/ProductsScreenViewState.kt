package com.example.pharmamanufacturer.presentation.products.main

import com.example.pharmamanufacturer.data.local.entities.Product

data class ProductsScreenViewState(
    val products: List<Product>,
    val selectedProduct: Product?,
    val visibleDialog: Boolean
) {
    companion object {
        val INIT: ProductsScreenViewState by lazy {
            ProductsScreenViewState(
                products = emptyList(),
                selectedProduct = null,
                visibleDialog = false
            )
        }
    }
}
