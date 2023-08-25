package com.example.pharmamanufacturer.presentation.compounds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Compound
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

class CompoundsViewModel : ViewModel() {

    private val _compoundsState = MutableStateFlow(emptyList<Compound>())
    val compoundsState: StateFlow<List<Compound>>
        get() {
            viewModelScope.launch(Dispatchers.IO) {
                _compoundsState.getAndUpdate {
                    getAllCompounds().sortedBy { !it.lowStock }
                }
            }
            return _compoundsState.asStateFlow()
        }

    private suspend fun getAllCompounds(): MutableList<Compound> {
        return DatabaseHandler.getAllCompounds()
    }
}
