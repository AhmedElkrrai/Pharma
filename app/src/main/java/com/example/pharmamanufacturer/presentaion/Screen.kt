package com.example.pharmamanufacturer.presentaion

sealed class Screen(val rout: String) {
    object ProductsScreen : Screen("products")
    object ComponentsScreen : Screen("components")
    object PackingScreen : Screen("packing")
}
