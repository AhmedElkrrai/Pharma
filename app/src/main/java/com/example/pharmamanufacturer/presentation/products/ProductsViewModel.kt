package com.example.pharmamanufacturer.presentation.products

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
    var isDialogShown by mutableStateOf(false)
        private set

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

    fun showDialog() {
        isDialogShown = true
    }

    fun dismissDialog() {
        isDialogShown = false
    }
}
