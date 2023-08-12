package com.example.pharmamanufacturer.presentation.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

class ComponentsViewModel : ViewModel() {

    private val _componentsState = MutableStateFlow(emptyList<ChemicalComponent>())
    val componentsState: StateFlow<List<ChemicalComponent>>
        get() {
            viewModelScope.launch(Dispatchers.IO) {
                _componentsState.getAndUpdate { getAllChemicalComponents() }
            }
            return _componentsState.asStateFlow()
        }

    private suspend fun getAllChemicalComponents(): MutableList<ChemicalComponent> {
        return DatabaseHandler.getAllChemicalComponents()
    }
}
