package com.example.pharmamanufacturer.presentation

const val COMPONENT_DETAILS_KEY = "component_details_key"

sealed class Screen(val rout: String) {
    object ProductsScreen : Screen("products")
    object ComponentsScreen : Screen("components")
    object ComponentDetailsScreen : Screen("component_details")
    object PackingScreen : Screen("packing")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(rout)
            args.forEach { arg -> append("/$arg") }
        }
    }
}
