package com.example.pharmamanufacturer.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent

@Dao
interface ChemicalComponentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(content: ChemicalComponent)

    @Query("SELECT * FROM ${ChemicalComponent.TABLE_CHEMICAL_COMPONENT}")
    suspend fun getAll(): List<ChemicalComponent>

    @Query("SELECT * FROM ${ChemicalComponent.TABLE_CHEMICAL_COMPONENT} " +
        "WHERE ${ChemicalComponent.COL_CHEMICAL_COMPONENT_ID} = :id LIMIT 1")
    suspend fun get(id: Int): ChemicalComponent?

    @Delete
    suspend fun delete(id: Int)

    @Update
    suspend fun update(contents: List<ChemicalComponent>)
}
