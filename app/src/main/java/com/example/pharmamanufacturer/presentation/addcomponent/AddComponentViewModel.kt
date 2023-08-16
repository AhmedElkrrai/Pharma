package com.example.pharmamanufacturer.presentation.addcomponent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import com.example.pharmamanufacturer.data.local.entities.Supplier
import com.example.pharmamanufacturer.presentation.addcomponent.action.AddComponentAction
import com.example.pharmamanufacturer.presentation.addcomponent.state.AddComponentEventState
import com.example.pharmamanufacturer.presentation.addcomponent.state.AddComponentScreenViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddComponentViewModel(
    private val ioContext: CoroutineContext = Dispatchers.IO,
    private val mainContext: CoroutineContext = Dispatchers.Main
) : ViewModel() {
    private val viewAction = Channel<AddComponentAction>()

    private val _viewState = MutableStateFlow(AddComponentScreenViewState.INIT)
    internal val viewState: StateFlow<AddComponentScreenViewState>
        get() = _viewState

    private val _events = Channel<AddComponentEventState>()
    internal val events: Flow<AddComponentEventState>
        get() = _events.receiveAsFlow()

    private val suppliers = mutableListOf<Supplier>()

    init {
        viewModelScope.launch {
            initViewData()
            processActions()
        }
    }

    private fun initViewData() {
        viewModelScope.launch(mainContext) {
            _viewState.value = AddComponentScreenViewState.INIT
        }
    }

    private suspend fun processActions() {
        viewModelScope.launch(ioContext) {
            viewAction.receiveAsFlow().collect { action ->
                when (action) {
                    is AddComponentAction.INSERT -> addComponent()
                    is AddComponentAction.AddSupplier -> addSupplier()
                    is AddComponentAction.KEYBOARD -> onKeyboardDone(action.invalidInput)
                }
            }
        }
    }

    private fun addComponent() {
        viewModelScope.launch(ioContext) {
            val name = viewState.value.name.input
            val amount = viewState.value.amount.input

            val component = ChemicalComponent(
                name = name,
                amount = amount.toDouble(),
                suppliers = suppliers.toList(),
                products = listOf()
            )
            DatabaseHandler.addChemicalComponent(component)
        }
    }

    private fun addSupplier() {
        val name = viewState.value.supplierName.input
        val capacity = viewState.value.capacity.input

        if (name.isBlank() || capacity.isBlank()) return

        val supplier = Supplier(
            name = name,
            capacity = capacity.toDouble()
        )
        suppliers.add(supplier)
    }

    private suspend fun onKeyboardDone(invalidInput: Boolean) {
        if (invalidInput) _events.send(AddComponentEventState.InvalidInput)
    }

    internal fun sendAction(action: AddComponentAction) {
        viewModelScope.launch {
            viewAction.send(action)
        }
    }
}
