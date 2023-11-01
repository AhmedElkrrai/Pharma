package com.example.pharmamanufacturer.presentation.compounds.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.core.Screen.Companion.COMPOUND_ID_KEY
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompoundDetailsViewModel @Inject constructor(
    private val db: DatabaseHandler,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val selectedCompoundId = savedStateHandle.get<Int>(COMPOUND_ID_KEY)

    private var _compoundState: MutableStateFlow<Compound?> = MutableStateFlow(null)
    val compoundState: StateFlow<Compound?>
        get() = _compoundState.asStateFlow()

    private var _productsState = MutableStateFlow(emptyList<Product>())
    val productsState: StateFlow<List<Product>>
        get() = _productsState.asStateFlow()

    init {
        initStates()
    }

    private fun initStates() {
        if (selectedCompoundId == null) return

        viewModelScope.launch {
            _compoundState.getAndUpdate {
                val compound = db.getCompound(selectedCompoundId)

                _productsState.getAndUpdate {
                    val productsIds = compound?.productNodes?.map { it.id } ?: listOf()
                    db.getProducts(productsIds)
                }

                compound
            }
        }
    }
}
