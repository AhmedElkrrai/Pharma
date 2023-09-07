package com.example.pharmamanufacturer.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pharmamanufacturer.data.local.entities.Batch

@Dao
interface BatchesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(batch: Batch): Long

    @Query("SELECT * FROM ${Batch.TABLE_BATCH}")
    suspend fun getAllBatches():List<Batch>
}
