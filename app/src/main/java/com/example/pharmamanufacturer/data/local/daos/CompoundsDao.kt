package com.example.pharmamanufacturer.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pharmamanufacturer.data.local.entities.Compound

@Dao
interface CompoundsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(compound: Compound)

    @Query("SELECT * FROM ${Compound.TABLE_COMPOUND}")
    suspend fun getAll(): List<Compound>

    @Query("SELECT * FROM ${Compound.TABLE_COMPOUND} " +
        "WHERE ${Compound.COL_COMPOUND_ID} = :id LIMIT 1")
    suspend fun get(id: Int): Compound?

    @Query("SELECT * FROM ${Compound.TABLE_COMPOUND} " +
        "WHERE ${Compound.COL_COMPOUND_NAME} = :name LIMIT 1")
    suspend fun getCompoundByName(name: String): Compound?

    @Update
    suspend fun update(compound: Compound)
}
