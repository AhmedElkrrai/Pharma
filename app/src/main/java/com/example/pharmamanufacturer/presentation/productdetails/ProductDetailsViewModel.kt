package com.example.pharmamanufacturer.presentation.productdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

class ProductDetailsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val selectedProductId = savedStateHandle.get<Int>(Screen.PRODUCT_DETAILS_KEY)

    private var _productState: MutableStateFlow<Product?> = MutableStateFlow(null)
    val productState: StateFlow<Product?>
        get() = _productState.asStateFlow()


    init {
        getProduct()
    }

    private fun getProduct() {
        if (selectedProductId == null) return

        viewModelScope.launch {
            _productState.getAndUpdate {
                DatabaseHandler.getProduct(selectedProductId)
            }
        }
    }
}
