package com.example.pharmamanufacturer.presentation.products

import com.example.pharmamanufacturer.data.local.entities.Product

interface ProductsScreenListener {
    fun onProductClick(product: Product)
    fun onAddClick()
}
