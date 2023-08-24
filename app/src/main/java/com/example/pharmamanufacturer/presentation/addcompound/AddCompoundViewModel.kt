package com.example.pharmamanufacturer.presentation.addcompound

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Supplier
import com.example.pharmamanufacturer.presentation.addcompound.action.AddCompoundAction
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldEventState
import com.example.pharmamanufacturer.presentation.addcompound.state.AddCompoundScreenViewState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldErrorEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState.Companion.CLEARED_FIELD
import com.example.pharmamanufacturer.presentation.addcompound.state.AddCompoundTextField
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

class AddCompoundViewModel(
    private val ioContext: CoroutineContext = Dispatchers.IO,
    private val mainContext: CoroutineContext = Dispatchers.Main,
    private val navigateBack: () -> Unit
) : ViewModel() {

    private val viewAction = Channel<AddCompoundAction>()

    private val _viewState = MutableStateFlow(AddCompoundScreenViewState.INIT)
    internal val viewState: StateFlow<AddCompoundScreenViewState>
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
            _viewState.value = AddCompoundScreenViewState.INIT
        }
    }

    private suspend fun processActions() {
        viewModelScope.launch(ioContext) {
            viewAction.receiveAsFlow().collect { action ->
                when (action) {
                    is AddCompoundAction.INSERT ->
                        addCompound()

                    is AddCompoundAction.AddSupplier ->
                        addSupplier()

                    is AddCompoundAction.KEYBOARD ->
                        renderTextFieldViewState(
                            action.textField,
                            TextFieldErrorEventState.ENTER
                        )

                    is AddCompoundAction.RetrieveInitialState ->
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
                    TextFieldEventState.InvalidInput(AddCompoundTextField.Name)
                )
                return@launch
            }
            if (amount.isBlank()) {
                _events.send(
                    TextFieldEventState.InvalidInput(AddCompoundTextField.Amount)
                )
                return@launch
            }

            val compound = Compound(
                name = name,
                availableAmount = amount.toDouble(),
                suppliers = suppliers.toList(),
                products = listOf()
            )
            DatabaseHandler.addCompound(compound)

            withContext(mainContext) {
                navigateBack.invoke()
            }
        }
    }

    private suspend fun addSupplier() {
        val name = viewState.value.supplierName.input
        val `package` = viewState.value.`package`.input

        if (name.isBlank()) {
            _events.send(
                TextFieldEventState.InvalidInput(AddCompoundTextField.SupplierName)
            )
            return
        }

        if (`package`.isBlank()) {
            _events.send(
                TextFieldEventState.InvalidInput(AddCompoundTextField.Package)
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

    internal fun sendAction(action: AddCompoundAction) {
        viewModelScope.launch {
            viewAction.send(action)
        }
    }

    private fun updateState(newState: (AddCompoundScreenViewState) -> AddCompoundScreenViewState) {
        _viewState.update { oldState -> newState(oldState) }
    }

    class Factory(private val navigateBack: () -> Unit) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            AddCompoundViewModel(navigateBack = navigateBack) as T
    }
}
