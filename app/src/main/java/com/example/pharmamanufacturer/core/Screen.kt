package com.example.pharmamanufacturer.core


sealed class Screen(val route: String) {
    object ProductsScreen : Screen(PRODUCTS_SCREEN_ROUTE)
    object ProductDetailsScreen : Screen(PRODUCT_DETAILS_SCREEN_ROUTE)
    object AddProductScreen : Screen(ADD_PRODUCT_SCREEN_ROUTE)
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

    companion object {
        private const val DETAILS = "_details"
        private const val ADD = "_add"
        const val COMPONENT_DETAILS_KEY = "component_details_key"
        const val PRODUCT_DETAILS_KEY = "product_details_key"
        const val PRODUCTS_SCREEN_ROUTE = "products"
        const val COMPONENTS_SCREEN_ROUTE = "components"
        const val PACKAGING_SCREEN_ROUTE = "packaging"
        const val COMPONENT_DETAILS_SCREEN_ROUTE = COMPONENTS_SCREEN_ROUTE + DETAILS
        const val PRODUCT_DETAILS_SCREEN_ROUTE = PRODUCTS_SCREEN_ROUTE + DETAILS
        const val ADD_COMPONENT_SCREEN_ROUTE = COMPONENTS_SCREEN_ROUTE + ADD
        const val ADD_PRODUCT_SCREEN_ROUTE = PRODUCTS_SCREEN_ROUTE + ADD
    }
}
