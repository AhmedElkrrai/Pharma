package com.example.pharmamanufacturer.data.local.database

import com.example.pharmamanufacturer.data.local.daos.ProductsDao

interface ProductsDatabase {
    fun productsDao(): ProductsDao
}
