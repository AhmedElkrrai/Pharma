package com.example.pharmamanufacturer.presentation.addcomponent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

class AddComponentViewModel : ViewModel() {
    private val _componentName = MutableStateFlow("")
    val componentName = _componentName.asStateFlow()

    private val _componentAmount = MutableStateFlow(0.0)
    val componentAmount = _componentAmount.asStateFlow()

    fun updateComponentName(name: String) {
        _componentName.getAndUpdate { name }
    }

    fun updateComponentAmount(amount: String) {
        if (amount.isEmpty())
            return

        _componentAmount.getAndUpdate {
            amount.toDouble()
        }
    }

    fun addComponent(component: ChemicalComponent) {
        viewModelScope.launch(Dispatchers.IO) {
            DatabaseHandler.addChemicalComponent(component)
        }
    }
}