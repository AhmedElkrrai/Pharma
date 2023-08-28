package com.example.pharmamanufacturer.presentation.products

interface ProductsScreenListener {
    fun onProductClick(productId: String)
    fun onProductionStarted(productId: String)
    fun onAddClick()
}
