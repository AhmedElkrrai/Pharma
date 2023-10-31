package com.example.pharmamanufacturer.presentation.packaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Packaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PackagingViewModel @Inject constructor(
    private val db: DatabaseHandler
) : ViewModel() {

    private val _packagingState = MutableStateFlow(emptyList<Packaging>())
    val packagingState: StateFlow<List<Packaging>>
        get() = _packagingState.asStateFlow()

    init {
        initViewState()
    }

    private fun initViewState() {
        viewModelScope.launch(Dispatchers.IO) {
            _packagingState.getAndUpdate {
                db.getPackagingList()
                    .toMutableList()
                    .sortedBy { !it.lowStock }
            }
        }
    }
}