package com.example.pharmamanufacturer.core

const val COMPONENT_DETAILS_KEY = "component_details_key"
const val DETAILS = "details"
const val PRODUCTS_SCREEN_ROUTE = "products"
const val COMPONENTS_SCREEN_ROUTE = "components"
const val PACKING_SCREEN_ROUTE = "packing"
const val COMPONENT_DETAILS_SCREEN_ROUTE = COMPONENTS_SCREEN_ROUTE + DETAILS

sealed class Screen(val route: String) {
    object ProductsScreen : Screen(PRODUCTS_SCREEN_ROUTE)
    object ComponentsScreen : Screen(COMPONENTS_SCREEN_ROUTE)
    object ComponentDetailsScreen : Screen(COMPONENT_DETAILS_SCREEN_ROUTE)
    object PackingScreen : Screen(PACKING_SCREEN_ROUTE)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}
