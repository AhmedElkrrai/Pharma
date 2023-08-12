package com.example.pharmamanufacturer.presentation.addcomponent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddComponentViewModel : ViewModel() {
    fun addComponent(component: ChemicalComponent) {
        viewModelScope.launch(Dispatchers.IO) {
            DatabaseHandler.addChemicalComponent(component)
        }
    }
}