package com.example.pharmamanufacturer.data.local.database

import com.example.pharmamanufacturer.core.PharmaApp
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@get:Synchronized
private val pharmaDatabase by lazy {
    PharmaDatabase.getInstance(PharmaApp.instance.applicationContext)
}

object DatabaseHandler {

    suspend fun getAllChemicalComponents(): MutableList<ChemicalComponent> {
        return withContext(Dispatchers.IO) {
            pharmaDatabase.chemicalComponentDao().getAll()
        }.toMutableList()
    }

    suspend fun addChemicalComponent(component: ChemicalComponent) {
        pharmaDatabase.chemicalComponentDao().insert(component)
    }
}
