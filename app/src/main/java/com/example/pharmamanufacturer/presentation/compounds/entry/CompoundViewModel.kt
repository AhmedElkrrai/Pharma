package com.example.pharmamanufacturer.presentation.compounds.entry

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.core.capitalizeFirstChar
import com.example.pharmamanufacturer.data.di.IOContext
import com.example.pharmamanufacturer.data.di.MainContext
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Supplier
import com.example.pharmamanufacturer.presentation.compounds.entry.action.CompoundAction
import com.example.pharmamanufacturer.presentation.compounds.entry.state.CompoundScreenViewState
import com.example.pharmamanufacturer.presentation.compounds.entry.state.CompoundTextField
import com.example.pharmamanufacturer.presentation.compounds.entry.state.renderViewState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldErrorEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState.Companion.CLEARED_FIELD
import dagger.hilt.android.lifecycle.HiltViewModel
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

@HiltViewModel
class CompoundViewModel @Inject constructor(
    @IOContext private val ioContext: CoroutineContext,
    @MainContext private val mainContext: CoroutineContext,
    private val db: DatabaseHandler,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val selectedId: Int? = savedStateHandle.get<Int>(Screen.COMPOUND_ID_KEY)
    private val compoundName: String? = savedStateHandle.get<String>(Screen.COMPOUND_NAME_KEY)

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
        if (compoundName == null) return
        updateState {
            it.copy(
                name = it.name.copy(
                    input = compoundName
                )
            )
        }
    }

    private suspend fun processActions() {
        viewModelScope.launch(ioContext) {
            viewAction.receiveAsFlow().collect { action ->
                when (action) {
                    is CompoundAction.INSERT ->
                        addCompound(action.navigateBack)

                    is CompoundAction.UPDATE ->
                        updateCompound(action.navigateBack)

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
                    is TextFieldEventState.InvalidInput ->
                        renderTextFieldViewState(
                            event.textField,
                            TextFieldErrorEventState.ENTER
                        )
                }
            }
        }
    }

    private fun addCompound(navigateBack: () -> Unit) {
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

    private fun updateCompound(navigateBack: () -> Unit) {
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
            val availableBatches = availableAmount / node.neededAmount

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

        clearSupplierInputs()
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
}
