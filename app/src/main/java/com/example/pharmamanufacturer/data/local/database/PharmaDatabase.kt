package com.example.pharmamanufacturer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pharmamanufacturer.data.local.entities.Batch
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.data.utils.Converters

@Database(
    entities = [Compound::class, Product::class, Batch::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PharmaDatabase :
    RoomDatabase(),
    CompoundsDatabase,
    ProductsDatabase,
    BatchesDatabase {

    companion object {
        const val DATABASE_NAME = "pharma_database"
    }
}
