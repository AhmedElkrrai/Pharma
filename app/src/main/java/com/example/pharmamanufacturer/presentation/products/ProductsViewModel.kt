package com.example.pharmamanufacturer.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    private val _productsState = MutableStateFlow(emptyList<Product>())
    val productsState: StateFlow<List<Product>>
        get() {
            viewModelScope.launch(Dispatchers.IO) {
                _productsState.getAndUpdate {
                    getAllProducts().sortedBy { !it.lowStock }
                }
            }
            return _productsState.asStateFlow()
        }

    private suspend fun getAllProducts(): MutableList<Product> {
        return DatabaseHandler.getAllProducts()
    }
}
