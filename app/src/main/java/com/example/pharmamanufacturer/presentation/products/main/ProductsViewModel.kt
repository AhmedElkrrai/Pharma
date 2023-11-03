package com.example.pharmamanufacturer.presentation.products.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.di.IOContext
import com.example.pharmamanufacturer.data.di.MainContext
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.products.dialog.ProductionDialogAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val db: DatabaseHandler,
    @IOContext private val ioContext: CoroutineContext,
) : ViewModel() {

    private val viewAction = Channel<ProductionDialogAction.Display>()

    private val _viewState = MutableStateFlow(ProductsScreenViewState.INIT)
    internal val viewState: StateFlow<ProductsScreenViewState>
        get() = updateViewState()

    init {
        processActions()
    }

    private fun updateViewState(): MutableStateFlow<ProductsScreenViewState> {
        viewModelScope.launch(ioContext) {
            val products = db.getAllProducts()
                .toMutableList()
                .sortedBy { !it.lowStock }

            _viewState.getAndUpdate {
                it.copy(
                    products = products
                )
            }
        }
        return _viewState
    }

    private fun processActions() {
        viewModelScope.launch {
            viewAction.receiveAsFlow().collect { action ->
                when (action) {
                    is ProductionDialogAction.Display.SHOW -> showDialog(action.product)
                    is ProductionDialogAction.Display.DISMISS -> dismissDialog(action.onDismiss)
                }
            }
        }
    }

    private fun showDialog(product: Product) {
        _viewState.getAndUpdate {
            it.copy(
                selectedProduct = product,
                visibleDialog = true
            )
        }
    }

    private fun dismissDialog(onDismiss: () -> Unit) {
        _viewState.getAndUpdate {
            it.copy(
                selectedProduct = null,
                visibleDialog = false
            )
        }
        onDismiss.invoke()
    }

    internal fun sendAction(action: ProductionDialogAction.Display) {
        viewModelScope.launch {
            viewAction.send(action)
        }
    }
}
