package com.example.pharmamanufacturer.presentation.productdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

class ProductDetailsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val selectedProductId = savedStateHandle.get<Int>(Screen.PRODUCT_ID_KEY)

    private var _productState: MutableStateFlow<Product?> = MutableStateFlow(null)
    val productState: StateFlow<Product?>
        get() = _productState.asStateFlow()

    private var _compoundsState: MutableStateFlow<List<Compound>> = MutableStateFlow(listOf())
    val compoundsState: StateFlow<List<Compound>>
        get() = _compoundsState.asStateFlow()


    init {
        initStates()
    }

    private fun initStates() {
        if (selectedProductId == null) return

        viewModelScope.launch {
            _productState.getAndUpdate {
                val product = DatabaseHandler.getProduct(selectedProductId)

                _compoundsState.getAndUpdate {
                    val compoundsIds = product?.compoundNodes?.map { it.id } ?: listOf()
                    DatabaseHandler.getCompounds(compoundsIds)
                }

                product
            }
        }
    }
}
