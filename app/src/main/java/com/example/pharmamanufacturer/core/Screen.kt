package com.example.pharmamanufacturer.core

const val COMPONENT_DETAILS_KEY = "component_details_key"
private const val DETAILS = "details"
private const val ADD = "add"
const val PRODUCTS_SCREEN_ROUTE = "products"
const val COMPONENTS_SCREEN_ROUTE = "components"
const val PACKAGING_SCREEN_ROUTE = "packaging"
const val COMPONENT_DETAILS_SCREEN_ROUTE = COMPONENTS_SCREEN_ROUTE + DETAILS
const val ADD_COMPONENT_SCREEN_ROUTE = ADD + COMPONENTS_SCREEN_ROUTE

sealed class Screen(val route: String) {
    object ProductsScreen : Screen(PRODUCTS_SCREEN_ROUTE)
    object ComponentsScreen : Screen(COMPONENTS_SCREEN_ROUTE)
    object ComponentDetailsScreen : Screen(COMPONENT_DETAILS_SCREEN_ROUTE)
    object AddComponentScreen : Screen(ADD_COMPONENT_SCREEN_ROUTE)
    object PackagingScreen : Screen(PACKAGING_SCREEN_ROUTE)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}
