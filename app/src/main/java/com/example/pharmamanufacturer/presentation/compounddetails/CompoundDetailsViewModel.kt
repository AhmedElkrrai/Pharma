package com.example.pharmamanufacturer.presentation.compounddetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.core.Screen.Companion.COMPOUND_DETAILS_KEY
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompoundDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val selectedCompound = savedStateHandle.get<Compound>(COMPOUND_DETAILS_KEY)

    private var _productsState = MutableStateFlow(emptyList<Product>())
    private val productsState: StateFlow<List<Product>>
        get() = _productsState.asStateFlow()

    fun getCompoundProducts(productsIds: List<Int>): StateFlow<List<Product>> {
        viewModelScope.launch {
            _productsState.getAndUpdate {
                DatabaseHandler.getProducts(productsIds)
            }
        }
        return productsState
    }
}
