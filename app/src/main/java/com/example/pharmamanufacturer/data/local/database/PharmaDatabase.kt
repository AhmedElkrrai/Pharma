package com.example.pharmamanufacturer.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent

@Database(
    entities = [ChemicalComponent::class],
    version = 1,
    exportSchema = false
)
abstract class PharmaDatabase :
    RoomDatabase(),
    ChemicalComponentDatabase {

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
