package com.example.pharmamanufacturer.core

const val MINIMUM_PRODUCT_INGREDIENTS = 2
const val MINIMUM_PRODUCT_BATCHES = 2

private const val DETAILS = "_details"
private const val ADD = "_add"
private const val EDIT = "_edit"
const val COMPOUND_ID_KEY = "compound_id_key"
const val COMPOUND_NAME_KEY = "compound_name_key"
const val PACKAGING_TYPE_KEY = "packaging_type_key"
const val PRODUCT_ID_KEY = "product_details_key"
const val PRODUCT_NAME_KEY = "product_name_key"
const val PRODUCTS_SCREEN_ROUTE = "products"
const val COMPOUND_SCREEN_ROUTE = "compounds"
const val PACKAGING_SCREEN_ROUTE = "packaging"
const val DASHBOARD_SCREEN_ROUTE = "dashboard"
const val PACKAGING_DETAILS_SCREEN_ROUTE = PACKAGING_SCREEN_ROUTE + DETAILS
const val EDIT_PACKAGING_SCREEN_ROUTE = PACKAGING_SCREEN_ROUTE + EDIT
const val COMPOUND_DETAILS_SCREEN_ROUTE = COMPOUND_SCREEN_ROUTE + DETAILS
const val PRODUCT_DETAILS_SCREEN_ROUTE = PRODUCTS_SCREEN_ROUTE + DETAILS
const val ADD_COMPOUND_SCREEN_ROUTE = COMPOUND_SCREEN_ROUTE + ADD
const val ADD_PRODUCT_SCREEN_ROUTE = PRODUCTS_SCREEN_ROUTE + ADD
const val EDIT_COMPOUND_SCREEN_ROUTE = COMPOUND_SCREEN_ROUTE + EDIT
const val EDIT_PRODUCT_SCREEN_ROUTE = PRODUCTS_SCREEN_ROUTE + EDIT
const val PRODUCTS_GRAPH_ROUTE = "products_graph_route"
const val COMPOUNDS_GRAPH_ROUTE = "compounds_graph_route"
const val PACKAGING_GRAPH_ROUTE = "packaging_graph_route"
const val ROOT_GRAPH_ROUTE = "root_route"