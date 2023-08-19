package com.example.pharmamanufacturer.data.local.database

import com.example.pharmamanufacturer.core.PharmaApp
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import com.example.pharmamanufacturer.data.local.entities.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@get:Synchronized
private val pharmaDatabase by lazy {
    PharmaDatabase.getInstance(PharmaApp.instance.applicationContext)
}

object DatabaseHandler {

    suspend fun getAllChemicalComponents(): MutableList<ChemicalComponent> {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.chemicalComponentDao().getAll()
        }.toMutableList()
    }

    suspend fun addChemicalComponent(component: ChemicalComponent) {
        pharmaDatabase.chemicalComponentDao().insert(component)
    }

    suspend fun getAllProducts(): MutableList<Product> {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.productsDao().getAll()
        }.toMutableList()
    }

    suspend fun addProduct(product: Product) {
        pharmaDatabase.productsDao().insert(product)
    }
}
