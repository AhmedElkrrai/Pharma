package com.example.pharmamanufacturer.presentation.products.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.data.di.IOContext
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _viewState: MutableStateFlow<ProductDetailsViewState> =
        MutableStateFlow(ProductDetailsViewState.INIT)
    val viewState: StateFlow<ProductDetailsViewState>
        get() = _viewState

    private val selectedProductId = savedStateHandle.get<Int>(Screen.PRODUCT_ID_KEY)

    private val viewAction = Channel<ProductDetailsAction>()

    init {
        viewModelScope.launch {
            initViewState()
            processActions()
        }
    }

    private fun initViewState() {
        if (selectedProductId == null) return

        viewModelScope.launch(ioContext) {
            val product = db.getProduct(selectedProductId)
            val compoundsIds = product?.compoundNodes?.map { it.id } ?: listOf()
            val packagingListIds = product?.packagingNodes?.map { it.id } ?: listOf()
            val compounds = db.getCompounds(compoundsIds)
            val packagingList = db.getPackagingListByIds(packagingListIds)

            _viewState.getAndUpdate {
                it.copy(
                    product = product,
                    compounds = compounds,
                    packagingList = packagingList
                )
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
        _viewState.getAndUpdate {
            it.copy(selectedTab = tab)
        }
    }

    internal fun sendAction(action: ProductDetailsAction) {
        viewModelScope.launch {
            viewAction.send(action)
        }
    }
}
