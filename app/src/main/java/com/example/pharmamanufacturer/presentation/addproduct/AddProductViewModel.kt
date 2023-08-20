package com.example.pharmamanufacturer.presentation.addproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.addproduct.action.AddProductAction
import com.example.pharmamanufacturer.presentation.addproduct.state.AddProductScreenViewState
import com.example.pharmamanufacturer.presentation.addproduct.state.AddProductTextField
import com.example.pharmamanufacturer.presentation.addproduct.state.renderViewState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldErrorEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState
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

class AddProductViewModel(
    private val ioContext: CoroutineContext = Dispatchers.IO,
    private val mainContext: CoroutineContext = Dispatchers.Main,
    private val navigateBack: () -> Unit
) : ViewModel() {

    private val viewAction = Channel<AddProductAction>()

    private val _viewState = MutableStateFlow(AddProductScreenViewState.INIT)
    internal val viewState: StateFlow<AddProductScreenViewState>
        get() = _viewState

    private val _events = Channel<TextFieldEventState>()
    private val events: Flow<TextFieldEventState>
        get() = _events.receiveAsFlow()

    private val compounds = mutableListOf<Compound>()

    init {
        viewModelScope.launch {
            initViewData()
            processActions()
            processEvents()
        }
    }

    private fun initViewData() {
        viewModelScope.launch(mainContext) {
            _viewState.value = AddProductScreenViewState.INIT
        }
    }

    private suspend fun processActions() {
        viewModelScope.launch(ioContext) {
            viewAction.receiveAsFlow().collect { action ->
                when (action) {
                    is AddProductAction.INSERT ->
                        addProduct()

                    is AddProductAction.AddCompound ->
                        addCompound()

                    is AddProductAction.KEYBOARD ->
                        renderTextFieldViewState(
                            action.textField,
                            TextFieldErrorEventState.ENTER
                        )

                    is AddProductAction.RetrieveInitialState ->
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
                        clearCompoundInputs()

                    is TextFieldEventState.InvalidInput ->
                        renderTextFieldViewState(
                            event.textField,
                            TextFieldErrorEventState.ENTER
                        )
                }
            }
        }
    }

    private fun addProduct() {
        viewModelScope.launch(ioContext) {
            val name = viewState.value.name.input

            if (name.isBlank()) {
                _events.send(
                    TextFieldEventState.InvalidInput(AddProductTextField.Name)
                )
                return@launch
            }

            if (compounds.isEmpty()) {
                val incompleteEntry = checkCompoundEntry(
                    compoundName = viewState.value.compoundName.input,
                    concentration = viewState.value.concentration.input
                )

                if (incompleteEntry) return@launch
            }

            val product = Product(
                name = name,
                compounds = compounds,
                batches = listOf()
            )
            DatabaseHandler.addProduct(product)

            withContext(mainContext) {
                navigateBack.invoke()
            }
        }
    }

    private suspend fun addCompound() {
        val name = viewState.value.compoundName.input
        val concentration = viewState.value.concentration.input

        val incompleteEntry = checkCompoundEntry(
            compoundName = name,
            concentration = concentration
        )

        if (incompleteEntry) return

        val compound = Compound(
            name = name,
            amount = concentration.toDouble()
        )
        compounds.add(compound)

        _events.send(TextFieldEventState.ClearSubInputs)
    }

    private suspend fun checkCompoundEntry(
        compoundName: String,
        concentration: String
    ): Boolean {
        if (compoundName.isBlank()) {
            _events.send(
                TextFieldEventState.InvalidInput(AddProductTextField.CompoundName)
            )
        }

        if (concentration.isBlank()) {
            _events.send(
                TextFieldEventState.InvalidInput(AddProductTextField.Concentration)
            )
        }
        return compoundName.isBlank() || concentration.isBlank()
    }

    private fun clearCompoundInputs() {
        updateState {
            it.copy(
                compoundName = it.compoundName.copy(
                    input = TextFieldViewState.CLEARED_FIELD
                ),
                concentration = it.concentration.copy(
                    input = TextFieldViewState.CLEARED_FIELD
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

    internal fun sendAction(action: AddProductAction) {
        viewModelScope.launch {
            viewAction.send(action)
        }
    }

    private fun updateState(newState: (AddProductScreenViewState) -> AddProductScreenViewState) {
        _viewState.update { oldState -> newState(oldState) }
    }

    class Factory(private val navigateBack: () -> Unit) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            AddProductViewModel(navigateBack = navigateBack) as T
    }
}