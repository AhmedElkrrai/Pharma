package com.example.pharmamanufacturer.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pharmamanufacturer.data.local.entities.Packaging

@Dao
interface PackagingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(packaging: Packaging): Long

    @Query("SELECT * FROM ${Packaging.TABLE_PACKAGING}")
    suspend fun getPackagingList(): List<Packaging>

    @Query("SELECT * FROM ${Packaging.TABLE_PACKAGING} WHERE id IN (:ids)")
    suspend fun getPackagingListByIds(ids: List<Int>): List<Packaging>

    @Query(
        "SELECT * FROM ${Packaging.TABLE_PACKAGING} " +
            "WHERE ${Packaging.COL_PACKAGING_ID} = :id LIMIT 1"
    )
    suspend fun getPackaging(id: Int): Packaging?

    @Query(
        "SELECT * FROM ${Packaging.TABLE_PACKAGING} " +
            "WHERE ${Packaging.COL_PACKAGING_TYPE} = :type LIMIT 1"
    )
    suspend fun getPackagingByType(type: String): Packaging?

    @Update
    suspend fun update(packaging: Packaging)
}