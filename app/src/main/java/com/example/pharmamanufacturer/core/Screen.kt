package com.example.pharmamanufacturer.core

sealed class Screen(val route: String) {
    object ProductsScreen : Screen(PRODUCTS_SCREEN_ROUTE)
    object ProductDetailsScreen : Screen(PRODUCT_DETAILS_SCREEN_ROUTE)
    object AddProductScreen : Screen(ADD_PRODUCT_SCREEN_ROUTE)
    object EditProductScreen : Screen(EDIT_PRODUCT_SCREEN_ROUTE)
    object CompoundsScreen : Screen(COMPOUND_SCREEN_ROUTE)
    object CompoundDetailsScreen : Screen(COMPOUND_DETAILS_SCREEN_ROUTE)
    object AddCompoundScreen : Screen(ADD_COMPOUND_SCREEN_ROUTE)
    object EditCompoundScreen : Screen(EDIT_COMPOUND_SCREEN_ROUTE)
    object DashboardScreen : Screen(DASHBOARD_SCREEN_ROUTE)
    object PackagingScreen : Screen(PACKAGING_SCREEN_ROUTE)
    object EditPackagingScreen : Screen(EDIT_PACKAGING_SCREEN_ROUTE)
    object PackagingDetailsScreen : Screen(PACKAGING_DETAILS_SCREEN_ROUTE)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }
}
