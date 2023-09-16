package com.example.pharmamanufacturer.presentation.compoundentry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.core.capitalizeFirstChar
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Supplier
import com.example.pharmamanufacturer.presentation.compoundentry.action.CompoundAction
import com.example.pharmamanufacturer.presentation.compoundentry.state.CompoundScreenViewState
import com.example.pharmamanufacturer.presentation.compoundentry.state.CompoundTextField
import com.example.pharmamanufacturer.presentation.compoundentry.state.renderViewState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldErrorEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState.Companion.CLEARED_FIELD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CompoundViewModel(
    private val ioContext: CoroutineContext = Dispatchers.IO,
    private val mainContext: CoroutineContext = Dispatchers.Main,
    private val selectedId: Int?,
    private val navigateBack: () -> Unit
) : ViewModel() {

    @Inject
    lateinit var db: DatabaseHandler

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
                productNodes = listOf()
            )
            db.addCompound(compound)

            withContext(mainContext) {
                navigateBack.invoke()
            }
        }
    }

    private fun updateCompound() {
        if (selectedId == null) return

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

            val compound = db.getCompound(selectedId) ?: return@launch

            compound.suppliers?.let { suppliers.addAll(it) }

            db.updateCompound(
                compound = compound.copy(
                    name = name.capitalizeFirstChar(),
                    availableAmount = amount.toDouble(),
                    suppliers = suppliers.toList()
                )
            )

            updateCompoundProducts(compound, amount.toDouble())

            withContext(mainContext) {
                navigateBack.invoke()
            }
        }
    }

    private suspend fun updateCompoundProducts(compound: Compound, availableAmount: Double) {
        if (compound.productNodes == null)
            return

        for (node in compound.productNodes) {
            val availableBatches = availableAmount / node.concentration

            val product = db.getProduct(node.id)

            val compoundNode =
                product?.compoundNodes?.find { it.id == compound.id }
                    ?.copy(available = availableBatches) ?: continue

            val updatedCompoundNodes =
                product.compoundNodes
                    .filter { it.id != compound.id }
                    .toMutableList()

            updatedCompoundNodes.add(compoundNode)

            db.updateProduct(
                product.copy(
                    compoundNodes = updatedCompoundNodes
                )
            )
        }
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
        private val selectedId: Int? = null
    ) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CompoundViewModel(
                navigateBack = navigateBack,
                selectedId = selectedId
            ) as T
    }
}
