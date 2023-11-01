package com.example.pharmamanufacturer.presentation.compounds.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Compound
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompoundsViewModel @Inject constructor(
    private val db: DatabaseHandler
) : ViewModel() {

    private val _compoundsState = MutableStateFlow(emptyList<Compound>())
    val compoundsState: StateFlow<List<Compound>>
        get() = _compoundsState.asStateFlow()

    init {
        initViewState()
    }

    private fun initViewState() {
        viewModelScope.launch(Dispatchers.IO) {
            _compoundsState.getAndUpdate {
                db.getAllCompounds()
                    .toMutableList()
                    .sortedBy { !it.lowStock }
            }
        }
    }
}
