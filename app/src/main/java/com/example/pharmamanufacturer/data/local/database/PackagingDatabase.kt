package com.example.pharmamanufacturer.data.local.database

import com.example.pharmamanufacturer.data.local.daos.PackagingDao

interface PackagingDatabase {
    fun packagingDao(): PackagingDao
}
