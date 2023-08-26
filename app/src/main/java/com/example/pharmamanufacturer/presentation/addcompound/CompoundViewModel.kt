package com.example.pharmamanufacturer.presentation.addcompound

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.airbnb.lottie.utils.Logger
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.core.capitalizeFirstChar
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Supplier
import com.example.pharmamanufacturer.presentation.addcompound.action.CompoundAction
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldEventState
import com.example.pharmamanufacturer.presentation.addcompound.state.CompoundScreenViewState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldErrorEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState.Companion.CLEARED_FIELD
import com.example.pharmamanufacturer.presentation.addcompound.state.CompoundTextField
import com.example.pharmamanufacturer.presentation.addcompound.state.renderViewState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CompoundViewModel(
    private val ioContext: CoroutineContext = Dispatchers.IO,
    private val mainContext: CoroutineContext = Dispatchers.Main,
    private val savedStateHandle: SavedStateHandle?,
    private val navigateBack: () -> Unit
) : ViewModel() {

    private val viewAction = Channel<CompoundAction>()

    private val _viewState = MutableStateFlow(CompoundScreenViewState.INIT)
    internal val viewState: StateFlow<CompoundScreenViewState>
        get() = _viewState

    private val _events = Channel<TextFieldEventState>()
    private val events: Flow<TextFieldEventState>
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
            _viewState.value = CompoundScreenViewState.INIT
        }
    }

    private suspend fun processActions() {
        viewModelScope.launch(ioContext) {
            viewAction.receiveAsFlow().collect { action ->
                when (action) {
                    is CompoundAction.INSERT ->
                        addCompound()

                    is CompoundAction.UPDATE ->
                        updateCompound()

                    is CompoundAction.AddSupplier ->
                        addSupplier()

                    is CompoundAction.KEYBOARD ->
                        renderTextFieldViewState(
                            action.textField,
                            TextFieldErrorEventState.ENTER
                        )

                    is CompoundAction.RetrieveInitialState ->
                        renderTextFieldViewState(
                            action.textField,
                            TextFieldErrorEventState.EXIT
                        )
                }
            }
        }
    }

    private suspend fun processEvents() {
        viewModelScope.launch(mainContext) {
            events.collect { event ->
                when (event) {
                    is TextFieldEventState.ClearSubInputs ->
                        clearSupplierInputs()

                    is TextFieldEventState.InvalidInput ->
                        renderTextFieldViewState(
                            event.textField,
                            TextFieldErrorEventState.ENTER
                        )
                }
            }
        }
    }

    private fun addCompound() {
        viewModelScope.launch(ioContext) {
            val name = viewState.value.name.input
            val amount = viewState.value.amount.input

            if (name.isBlank()) {
                _events.send(
                    TextFieldEventState.InvalidInput(CompoundTextField.Name)
                )
                return@launch
            }
            if (amount.isBlank()) {
                _events.send(
                    TextFieldEventState.InvalidInput(CompoundTextField.Amount)
                )
                return@launch
            }

            val compound = Compound(
                name = name.capitalizeFirstChar(),
                availableAmount = amount.toDouble(),
                suppliers = suppliers.toList(),
                batches = listOf()
            )
            DatabaseHandler.addCompound(compound)

            withContext(mainContext) {
                navigateBack.invoke()
            }
        }
    }

    private fun updateCompound() {
        val selectedCompoundId = savedStateHandle?.get<Int>(Screen.COMPOUND_ID_KEY)
        Log.d("taggs", "selectedCompoundId = $selectedCompoundId")
    }

    private suspend fun addSupplier() {
        val name = viewState.value.supplierName.input
        val `package` = viewState.value.`package`.input

        if (name.isBlank()) {
            _events.send(
                TextFieldEventState.InvalidInput(CompoundTextField.SupplierName)
            )
            return
        }

        if (`package`.isBlank()) {
            _events.send(
                TextFieldEventState.InvalidInput(CompoundTextField.Package)
            )
            return
        }

        val supplier = Supplier(
            name = name,
            `package` = `package`.toDouble()
        )
        suppliers.add(supplier)

        _events.send(TextFieldEventState.ClearSubInputs)
    }

    private fun clearSupplierInputs() {
        updateState {
            it.copy(
                supplierName = it.supplierName.copy(
                    input = CLEARED_FIELD
                ),
                `package` = it.`package`.copy(
                    input = CLEARED_FIELD
                )
            )
        }
    }

    private fun renderTextFieldViewState(
        textField: TextField,
        errorEventState: TextFieldErrorEventState,
    ) {
        updateState {
            viewState.value.renderViewState(
                textField,
                errorEventState
            )
        }
    }

    internal fun sendAction(action: CompoundAction) {
        viewModelScope.launch {
            viewAction.send(action)
        }
    }

    private fun updateState(newState: (CompoundScreenViewState) -> CompoundScreenViewState) {
        _viewState.update { oldState -> newState(oldState) }
    }

    class Factory(
        private val navigateBack: () -> Unit,
        private val savedStateHandle: SavedStateHandle? = null
    ) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CompoundViewModel(
                navigateBack = navigateBack,
                savedStateHandle = savedStateHandle
            ) as T
    }
}
