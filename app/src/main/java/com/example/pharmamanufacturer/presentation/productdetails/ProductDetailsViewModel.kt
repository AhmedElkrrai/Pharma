package com.example.pharmamanufacturer.presentation.productdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.data.di.IOContext
import com.example.pharmamanufacturer.data.di.MainContext
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val db: DatabaseHandler,
    savedStateHandle: SavedStateHandle,
    @IOContext private val ioContext: CoroutineContext
) : ViewModel() {

    private val selectedProductId = savedStateHandle.get<Int>(Screen.PRODUCT_ID_KEY)

    private val viewAction = Channel<ProductDetailsAction>()

    private var _productState: MutableStateFlow<Product?> = MutableStateFlow(null)
    val productState: StateFlow<Product?>
        get() = _productState.asStateFlow()

    private var _compoundsState: MutableStateFlow<List<Compound>> = MutableStateFlow(listOf())
    val compoundsState: StateFlow<List<Compound>>
        get() = _compoundsState.asStateFlow()

    //TODO: assign and initialize packagingState

    private var _selectedTab: MutableStateFlow<ProductDetailsTab> =
        MutableStateFlow(ProductDetailsTab.COMPOUNDS)

    val selectedTab: StateFlow<ProductDetailsTab>
        get() = _selectedTab.asStateFlow()

    init {
        viewModelScope.launch {
            initStates()
            processActions()
        }
    }

    private fun initStates() {
        if (selectedProductId == null) return

        viewModelScope.launch(ioContext) {
            _productState.getAndUpdate {
                val product = db.getProduct(selectedProductId)

                _compoundsState.getAndUpdate {
                    val compoundsIds = product?.compoundNodes?.map { it.id } ?: listOf()
                    db.getCompounds(compoundsIds)
                }

                product
            }
        }
    }

    private suspend fun processActions() {
        viewModelScope.launch {
            viewAction.receiveAsFlow().collect { action ->
                when (action) {
                    is ProductDetailsAction.SelectTab -> selectTab(action.tab)
                }
            }
        }
    }

    private fun selectTab(tab: ProductDetailsTab) {
        _selectedTab.getAndUpdate { tab }
    }

    internal fun sendAction(action: ProductDetailsAction) {
        viewModelScope.launch {
            viewAction.send(action)
        }
    }
}
