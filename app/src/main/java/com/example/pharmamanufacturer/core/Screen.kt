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

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }

    companion object {
        private const val DETAILS = "_details"
        private const val ADD = "_add"
        private const val EDIT = "_edit"
        const val COMPOUND_ID_KEY = "compound_id_key"
        const val COMPOUND_NAME_KEY = "compound_name_key"
        const val PRODUCT_ID_KEY = "product_details_key"
        const val PRODUCT_NAME_KEY = "product_name_key"
        const val PRODUCTS_SCREEN_ROUTE = "products"
        const val COMPOUND_SCREEN_ROUTE = "compounds"
        const val PACKAGING_SCREEN_ROUTE = "packaging"
        const val DASHBOARD_SCREEN_ROUTE = "dashboard"
        const val COMPOUND_DETAILS_SCREEN_ROUTE = COMPOUND_SCREEN_ROUTE + DETAILS
        const val PRODUCT_DETAILS_SCREEN_ROUTE = PRODUCTS_SCREEN_ROUTE + DETAILS
        const val ADD_COMPOUND_SCREEN_ROUTE = COMPOUND_SCREEN_ROUTE + ADD
        const val ADD_PRODUCT_SCREEN_ROUTE = PRODUCTS_SCREEN_ROUTE + ADD
        const val EDIT_COMPOUND_SCREEN_ROUTE = COMPOUND_SCREEN_ROUTE + EDIT
        const val EDIT_PRODUCT_SCREEN_ROUTE = PRODUCTS_SCREEN_ROUTE + EDIT
    }
}
