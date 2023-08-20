package com.example.pharmamanufacturer.core

sealed class Screen(val route: String) {
    object ProductsScreen : Screen(PRODUCTS_SCREEN_ROUTE)
    object ProductDetailsScreen : Screen(PRODUCT_DETAILS_SCREEN_ROUTE)
    object AddProductScreen : Screen(ADD_PRODUCT_SCREEN_ROUTE)
    object CompoundsScreen : Screen(COMPOUND_SCREEN_ROUTE)
    object CompoundDetailsScreen : Screen(COMPOUND_DETAILS_SCREEN_ROUTE)
    object AddCompoundScreen : Screen(ADD_COMPOUND_SCREEN_ROUTE)
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
        const val COMPOUND_DETAILS_KEY = "compound_details_key"
        const val PRODUCT_DETAILS_KEY = "product_details_key"
        const val PRODUCTS_SCREEN_ROUTE = "products"
        const val COMPOUND_SCREEN_ROUTE = "compounds"
        const val PACKAGING_SCREEN_ROUTE = "packaging"
        const val COMPOUND_DETAILS_SCREEN_ROUTE = COMPOUND_SCREEN_ROUTE + DETAILS
        const val PRODUCT_DETAILS_SCREEN_ROUTE = PRODUCTS_SCREEN_ROUTE + DETAILS
        const val ADD_COMPOUND_SCREEN_ROUTE = COMPOUND_SCREEN_ROUTE + ADD
        const val ADD_PRODUCT_SCREEN_ROUTE = PRODUCTS_SCREEN_ROUTE + ADD
    }
}
