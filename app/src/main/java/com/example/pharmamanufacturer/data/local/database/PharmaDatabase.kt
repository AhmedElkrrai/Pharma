package com.example.pharmamanufacturer.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.data.utils.Converters

@Database(
    entities = [Compound::class, Product::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PharmaDatabase :
    RoomDatabase(),
    CompoundsDatabase,
    ProductsDatabase {

    companion object {
        private const val DATABASE_NAME = "pharma_database"

        @Volatile
        private var INSTANCE: PharmaDatabase? = null

        fun getInstance(context: Context): PharmaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    PharmaDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
