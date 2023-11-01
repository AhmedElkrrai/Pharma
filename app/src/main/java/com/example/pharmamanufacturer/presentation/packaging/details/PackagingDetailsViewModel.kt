package com.example.pharmamanufacturer.presentation.packaging.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.data.di.IOContext
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class PackagingDetailsViewModel @Inject constructor(
    private val db: DatabaseHandler,
    savedStateHandle: SavedStateHandle,
    @IOContext private val ioContext: CoroutineContext
) : ViewModel() {
    private val packagingType = savedStateHandle.get<String>(Screen.PACKAGING_TYPE_KEY)

    private val _viewState: MutableStateFlow<PackagingDetailsViewState> =
        MutableStateFlow(PackagingDetailsViewState.INIT)
    val viewState: StateFlow<PackagingDetailsViewState>
        get() = _viewState


    init {
        initViewState()
    }

    private fun initViewState() {
        if (packagingType == null) return

        viewModelScope.launch(ioContext) {
            val packaging = db.getPackagingByType(packagingType)
            val productsIds = packaging?.productNodes?.map { it.id } ?: listOf()
            val products = db.getProducts(productsIds)

            _viewState.getAndUpdate {
                it.copy(
                    packaging = packaging,
                    products = products
                )
            }
        }
    }
}
