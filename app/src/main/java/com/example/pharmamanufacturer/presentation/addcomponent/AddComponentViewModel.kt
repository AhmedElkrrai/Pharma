package com.example.pharmamanufacturer.presentation.addcomponent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import com.example.pharmamanufacturer.data.local.entities.Supplier
import com.example.pharmamanufacturer.presentation.addcomponent.action.AddComponentAction
import com.example.pharmamanufacturer.presentation.addcomponent.state.AddComponentEventState
import com.example.pharmamanufacturer.presentation.addcomponent.state.AddComponentScreenViewState
import com.example.pharmamanufacturer.presentation.addcomponent.state.FieldTextViewState.Companion.CLEARED_FIELD
import com.example.pharmamanufacturer.presentation.addcomponent.state.shouldEnterErrorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
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
    private val events: Flow<AddComponentEventState>
        get() = _events.receiveAsFlow()

    private val suppliers = mutableListOf<Supplier>()

    init {
        viewModelScope.launch {
            initViewData()
            processActions()
            processEvents()
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

    private suspend fun processEvents() {
        viewModelScope.launch(mainContext) {
            events.collect { event ->
                when (event) {
                    is AddComponentEventState.ClearSupplierInputs -> clearSupplierInputs()
                    is AddComponentEventState.FieldValueChanged -> onValueChanged()
                    is AddComponentEventState.InvalidInputState -> handleInvalidInput(event)
                }
            }
        }
    }

    private fun addComponent() {
        viewModelScope.launch(ioContext) {
            val name = viewState.value.name.input
            val amount = viewState.value.amount.input
            if (name.isBlank() || amount.isBlank()) {
                _events.send(
                    AddComponentEventState.InvalidInputState(
                        name = name.isBlank(),
                        amount = amount.isBlank()
                    )
                )
                return@launch
            }

            val component = ChemicalComponent(
                name = name,
                amount = amount.toDouble(),
                suppliers = suppliers.toList(),
                products = listOf()
            )
            DatabaseHandler.addChemicalComponent(component)
        }
    }

    private suspend fun addSupplier() {
        val name = viewState.value.supplierName.input
        val capacity = viewState.value.capacity.input

        if (name.isBlank() || capacity.isBlank()) {
            _events.send(
                AddComponentEventState.InvalidInputState(
                    supplierName = name.isBlank(),
                    capacity = capacity.isBlank()
                )
            )
            return
        }

        val supplier = Supplier(
            name = name,
            capacity = capacity.toDouble()
        )
        suppliers.add(supplier)

        _events.send(AddComponentEventState.ClearSupplierInputs)
    }

    private suspend fun onKeyboardDone(invalidInputState: AddComponentEventState.InvalidInputState) {
        _events.send(
            AddComponentEventState.InvalidInputState(
                name = invalidInputState.name,
                amount = invalidInputState.amount,
                supplierName = invalidInputState.supplierName,
                capacity = invalidInputState.capacity
            )
        )
    }

    private fun clearSupplierInputs() {
        updateState {
            it.copy(
                supplierName = it.supplierName.copy(
                    input = CLEARED_FIELD
                ),
                capacity = it.capacity.copy(
                    input = CLEARED_FIELD
                )
            )
        }
    }

    private fun onValueChanged() {
        viewState.value.supplierName.input = "Weeeee"
        viewState.value.capacity.input = "Weeeee"
    }

    private fun handleInvalidInput(invalidInputState: AddComponentEventState.InvalidInputState) {
        updateState {
            viewState.value.shouldEnterErrorState(invalidInputState)
        }
    }

    internal fun sendAction(action: AddComponentAction) {
        viewModelScope.launch {
            viewAction.send(action)
        }
    }

    private fun updateState(newState: (AddComponentScreenViewState) -> AddComponentScreenViewState) {
        _viewState.update { oldState -> newState(oldState) }
    }
}
