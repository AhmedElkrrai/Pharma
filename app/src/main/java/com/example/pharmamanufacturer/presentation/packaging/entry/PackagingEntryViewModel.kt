package com.example.pharmamanufacturer.presentation.packaging.entry

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.core.capitalizeFirstChar
import com.example.pharmamanufacturer.data.di.IOContext
import com.example.pharmamanufacturer.data.di.MainContext
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Packaging
import com.example.pharmamanufacturer.data.local.entities.Supplier
import com.example.pharmamanufacturer.presentation.packaging.entry.action.PackagingAction
import com.example.pharmamanufacturer.presentation.packaging.entry.state.PackagingEntryScreenViewState
import com.example.pharmamanufacturer.presentation.packaging.entry.state.PackagingEntryTextField
import com.example.pharmamanufacturer.presentation.packaging.entry.state.renderViewState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextField
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldErrorEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState
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
class PackagingEntryViewModel @Inject constructor(
    @IOContext private val ioContext: CoroutineContext,
    @MainContext private val mainContext: CoroutineContext,
    private val db: DatabaseHandler,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val packagingType: String? =
        savedStateHandle.get<String>(Screen.PACKAGING_TYPE_KEY)

    private val viewAction = Channel<PackagingAction>()

    private val _viewState = MutableStateFlow(PackagingEntryScreenViewState.INIT)
    internal val viewState: StateFlow<PackagingEntryScreenViewState>
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
        if (packagingType == null) return
        updateState {
            it.copy(
                type = it.type.copy(
                    input = packagingType
                )
            )
        }
    }

    private suspend fun processActions() {
        viewModelScope.launch(ioContext) {
            viewAction.receiveAsFlow().collect { action ->
                when (action) {
                    is PackagingAction.UPDATE ->
                        updatePackaging(action.navigateBack)

                    is PackagingAction.AddSupplier ->
                        addSupplier()

                    is PackagingAction.KEYBOARD ->
                        renderTextFieldViewState(
                            action.textField,
                            TextFieldErrorEventState.ENTER
                        )

                    is PackagingAction.RetrieveInitialState ->
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

    private fun updatePackaging(navigateBack: () -> Unit) {
        if (packagingType == null) return

        viewModelScope.launch(ioContext) {
            val type = viewState.value.type.input
            val amount = viewState.value.amount.input

            if (type.isBlank()) {
                _events.send(
                    TextFieldEventState.InvalidInput(PackagingEntryTextField.Type)
                )
                return@launch
            }
            if (amount.isBlank()) {
                _events.send(
                    TextFieldEventState.InvalidInput(PackagingEntryTextField.Amount)
                )
                return@launch
            }

            val packaging = db.getPackagingByType(packagingType) ?: return@launch

            packaging.suppliers?.let { suppliers.addAll(it) }

            db.updatePackaging(
                packaging = packaging.copy(
                    type = type.capitalizeFirstChar(),
                    availableAmount = amount.toDouble(),
                    suppliers = suppliers.toList()
                )
            )

            updatePackagingProducts(packaging, amount.toDouble())

            withContext(mainContext) {
                navigateBack.invoke()
            }
        }
    }

    private suspend fun updatePackagingProducts(
        packaging: Packaging,
        availableAmount: Double
    ) {
        if (packaging.productNodes == null)
            return

        for (node in packaging.productNodes) {
            val availableBatches = availableAmount / node.neededAmount

            val product = db.getProduct(node.id)

            val packagingNode =
                product?.packagingNodes?.find { it.id == packaging.id }
                    ?.copy(available = availableBatches) ?: continue

            val updatedPackagingNodeNodes =
                product.packagingNodes
                    .filter { it.id != packaging.id }
                    .toMutableList()

            updatedPackagingNodeNodes.add(packagingNode)

            db.updateProduct(
                product.copy(
                    packagingNodes = updatedPackagingNodeNodes
                )
            )
        }
    }

    private suspend fun addSupplier() {
        val name = viewState.value.supplierName.input
        val `package` = viewState.value.`package`.input

        if (name.isBlank()) {
            _events.send(
                TextFieldEventState.InvalidInput(PackagingEntryTextField.SupplierName)
            )
            return
        }

        if (`package`.isBlank()) {
            _events.send(
                TextFieldEventState.InvalidInput(PackagingEntryTextField.Package)
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
                    input = TextFieldViewState.CLEARED_FIELD
                ),
                `package` = it.`package`.copy(
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

    internal fun sendAction(action: PackagingAction) {
        viewModelScope.launch {
            viewAction.send(action)
        }
    }

    private fun updateState(
        newState: (PackagingEntryScreenViewState) -> PackagingEntryScreenViewState
    ) {
        _viewState.update { oldState -> newState(oldState) }
    }
}