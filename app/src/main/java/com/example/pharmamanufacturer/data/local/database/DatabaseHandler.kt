package com.example.pharmamanufacturer.data.local.database

import com.example.pharmamanufacturer.data.local.daos.BatchesDao
import com.example.pharmamanufacturer.data.local.daos.CompoundsDao
import com.example.pharmamanufacturer.data.local.daos.ProductsDao
import com.example.pharmamanufacturer.data.local.entities.Batch
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseHandler @Inject constructor(
    private val batchesDao: BatchesDao,
    private val productsDao: ProductsDao,
    private val compoundsDao: CompoundsDao,
) {

    suspend fun insertBatch(batch: Batch): Long {
        return withContext(Dispatchers.IO) {
            batchesDao.insert(batch)
        }
    }

    suspend fun getAllBatches(): List<Batch> {
        return withContext(Dispatchers.IO) {
            batchesDao.getAllBatches()
        }
    }

    suspend fun getAllCompounds(): List<Compound> {
        return withContext(Dispatchers.IO) {
            compoundsDao.getAllCompounds()
        }
    }

    suspend fun getCompounds(ids: List<Int>): List<Compound> {
        return withContext(Dispatchers.IO) {
            compoundsDao.getCompounds(ids)
        }
    }

    suspend fun addCompound(compound: Compound): Long {
        return withContext(Dispatchers.IO) {
            compoundsDao.insert(compound)
        }
    }

    suspend fun updateCompound(compound: Compound) {
        withContext(Dispatchers.IO) {
            compoundsDao.update(compound)
        }
    }

    suspend fun getCompoundByName(name: String): Compound? {
        return withContext(Dispatchers.IO) {
            compoundsDao.getCompoundByName(name)
        }
    }

    suspend fun getCompound(id: Int): Compound? {
        return withContext(Dispatchers.IO) {
            compoundsDao.get(id)
        }
    }

    suspend fun getAllProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            productsDao.getAllProducts()
        }
    }

    suspend fun getProducts(ids: List<Int>): List<Product> {
        return withContext(Dispatchers.IO) {
            productsDao.getProducts(ids)
        }
    }

    suspend fun getProduct(id: Int): Product? {
        return withContext(Dispatchers.IO) {
            productsDao.get(id)
        }
    }

    suspend fun addProduct(product: Product): Long {
        return withContext(Dispatchers.IO) {
            productsDao.insert(product)
        }
    }

    suspend fun updateProduct(product: Product) {
        withContext(Dispatchers.IO) {
            productsDao.update(product)
        }
    }
}
