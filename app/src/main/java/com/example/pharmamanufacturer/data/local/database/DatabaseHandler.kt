package com.example.pharmamanufacturer.data.local.database

import com.example.pharmamanufacturer.core.PharmaApp
import com.example.pharmamanufacturer.data.local.entities.Batch
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@get:Synchronized
private val pharmaDatabase: PharmaDatabase by lazy {
    PharmaDatabase.getInstance(PharmaApp.instance.applicationContext)
}

object DatabaseHandler {

    suspend fun insertBatch(batch: Batch): Long {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.batchesDao().insert(batch)
        }
    }

    suspend fun getAllBatches(): List<Batch> {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.batchesDao().getAllBatches()
        }
    }

    suspend fun getAllCompounds(): List<Compound> {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.compoundsDao().getAllCompounds()
        }
    }

    suspend fun getCompounds(ids: List<Int>): List<Compound> {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.compoundsDao().getCompounds(ids)
        }
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

    suspend fun getCompoundByName(compoundName: String): Compound? {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.compoundsDao().getCompoundByName(compoundName)
        }
    }

    suspend fun getCompound(id: Int): Compound? {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.compoundsDao().get(id)
        }
    }

    suspend fun getAllProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.productsDao().getAllProducts()
        }
    }

    suspend fun getProducts(ids: List<Int>): List<Product> {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.productsDao().getProducts(ids)
        }
    }

    suspend fun getProduct(id: Int): Product? {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.productsDao().get(id)
        }
    }

    suspend fun addProduct(product: Product): Long {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.productsDao().insert(product)
        }
    }

    suspend fun updateProduct(product: Product) {
        withContext(Dispatchers.IO) {
            pharmaDatabase.productsDao().update(product)
        }
    }
}
