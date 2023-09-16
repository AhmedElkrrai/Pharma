package com.example.pharmamanufacturer.data.di

import android.content.Context
import androidx.room.Room
import com.example.pharmamanufacturer.data.local.daos.BatchesDao
import com.example.pharmamanufacturer.data.local.daos.CompoundsDao
import com.example.pharmamanufacturer.data.local.daos.ProductsDao
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.database.PharmaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun providePharmaDatabase(
        @ApplicationContext context: Context
    ): PharmaDatabase {
        return Room.databaseBuilder(
            context,
            PharmaDatabase::class.java,
            PharmaDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideBatchesDao(
        db: PharmaDatabase
    ): BatchesDao {
        return db.batchesDao()
    }

    @Provides
    fun provideProductsDao(
        db: PharmaDatabase
    ): ProductsDao {
        return db.productsDao()
    }

    @Provides
    fun provideCompoundDao(
        db: PharmaDatabase
    ): CompoundsDao {
        return db.compoundsDao()
    }

    @Provides
    fun provideDatabaseHandler(
        batchesDao: BatchesDao,
        productsDao: ProductsDao,
        compoundsDao: CompoundsDao
    ): DatabaseHandler {
        return DatabaseHandler(
            batchesDao,
            productsDao,
            compoundsDao
        )
    }
}
