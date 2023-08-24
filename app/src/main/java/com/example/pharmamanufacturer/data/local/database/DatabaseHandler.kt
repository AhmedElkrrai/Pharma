package com.example.pharmamanufacturer.data.local.database

import com.example.pharmamanufacturer.core.PharmaApp
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@get:Synchronized
private val pharmaDatabase by lazy {
    PharmaDatabase.getInstance(PharmaApp.instance.applicationContext)
}

object DatabaseHandler {

    suspend fun getAllCompounds(): MutableList<Compound> {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.compoundsDao().getAll()
        }.toMutableList()
    }

    suspend fun addCompound(compound: Compound): Long {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.compoundsDao().insert(compound)
        }
    }

    suspend fun updateCompound(compound: Compound) {
        withContext(Dispatchers.IO) {
            pharmaDatabase.compoundsDao().update(compound)
        }
    }

    suspend fun getCompound(compoundName: String): Compound? {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.compoundsDao().getCompoundByName(compoundName)
        }
    }

    suspend fun getAllProducts(): MutableList<Product> {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.productsDao().getAll()
        }.toMutableList()
    }

    suspend fun addProduct(product: Product): Long {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.productsDao().insert(product)
        }
    }
}
