package com.example.pharmamanufacturer.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pharmamanufacturer.data.local.entities.Product

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Query("SELECT * FROM ${Product.TABLE_PRODUCT}")
    suspend fun getAll(): List<Product>

    @Query("SELECT * FROM ${Product.TABLE_PRODUCT} " +
        "WHERE ${Product.COL_PRODUCT_ID} = :id LIMIT 1")
    suspend fun get(id: Int): Product?

    @Update
    suspend fun update(product: Product)
}
