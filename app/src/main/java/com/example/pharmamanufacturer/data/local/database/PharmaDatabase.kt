package com.example.pharmamanufacturer.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import com.example.pharmamanufacturer.data.utils.Converters

@Database(
    entities = [ChemicalComponent::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PharmaDatabase :
    RoomDatabase(),
    ComponentsDatabase {

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
