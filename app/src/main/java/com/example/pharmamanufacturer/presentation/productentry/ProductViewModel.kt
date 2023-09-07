package com.example.pharmamanufacturer.presentation.productentry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.core.MINIMUM_PRODUCT_INGREDIENTS
import com.example.pharmamanufacturer.core.capitalizeFirstChar
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.CompoundNode
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.data.local.entities.ProductNode
import com.example.pharmamanufacturer.presentation.productentry.action.ProductAction
import com.example.pharmamanufacturer.presentation.productentry.state.ProductScreenViewState
import com.example.pharmamanufacturer.presentation.productentry.state.ProductTextField
import com.example.pharmamanufacturer.presentation.productentry.state.renderViewState
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

class ProductViewModel(
    private val ioContext: CoroutineContext = Dispatchers.IO,
    private val mainContext: CoroutineContext = Dispatchers.Main,
    private val selectedId: Int?,
    private val navigateBack: () -> Unit
) : ViewModel() {

    private val viewAction = Channel<ProductAction>()

    private val _viewState = MutableStateFlow(ProductScreenViewState.INIT)
    internal val viewState: StateFlow<ProductScreenViewState>
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
            _viewState.value = ProductScreenViewState.INIT
        }
    }

    private suspend fun processActions() {
        viewModelScope.launch(ioContext) {
            viewAction.receiveAsFlow().collect { action ->
                when (action) {
                    is ProductAction.INSERT ->
                        addProduct()

                    is ProductAction.UPDATE ->
                        updateProduct()

                    is ProductAction.Compound ->
                        addCompound()

                    is ProductAction.KEYBOARD ->
                        renderTextFieldViewState(
                            action.textField,
                            TextFieldErrorEventState.ENTER
                        )

                    is ProductAction.RetrieveInitialState ->
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
                    TextFieldEventState.InvalidInput(ProductTextField.Name)
                )
                return@launch
            }

            if (compounds.size < MINIMUM_PRODUCT_INGREDIENTS) {
                checkCompoundEntry(
                    compoundName = viewState.value.compoundName.input,
                    concentration = viewState.value.concentration.input
                )

                return@launch
            }

            val product = Product(
                name = name.capitalizeFirstChar(),
                compoundNodes = listOf()
            )

            val productId = DatabaseHandler.addProduct(product)

            val compoundNodes = updateCompounds(productId.toInt())

            DatabaseHandler.updateProduct(
                product = product.copy(
                    id = productId.toInt(),
                    compoundNodes = compoundNodes
                )
            )

            withContext(mainContext) {
                navigateBack.invoke()
            }
        }
    }

    private fun updateProduct() {
        if (selectedId == null) return

        viewModelScope.launch(ioContext) {
            val name = viewState.value.name.input

            if (name.isBlank()) {
                _events.send(
                    TextFieldEventState.InvalidInput(ProductTextField.Name)
                )
                return@launch
            }

            val product = DatabaseHandler.getProduct(selectedId) ?: return@launch

            val newNodes = updateCompounds(product.id ?: return@launch)

            val updatedNodes = product.compoundNodes + newNodes

            DatabaseHandler.updateProduct(
                product = product.copy(
                    name = name.capitalizeFirstChar(),
                    compoundNodes = updatedNodes
                )
            )

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

        val compound =
            Compound(
                name = name.capitalizeFirstChar(),
                availableAmount = concentration.toDouble(),
                productNodes = listOf()
            )
        compounds.add(compound)

        _events.send(TextFieldEventState.ClearSubInputs)
    }

    private suspend fun updateCompounds(productId: Int): List<CompoundNode> {
        val productCompoundNodes = mutableListOf<CompoundNode>()

        compounds.forEach { enteredCompound ->
            val compound = DatabaseHandler.getCompoundByName(
                compoundName = enteredCompound.name
            )

            val newProductNode = ProductNode(
                id = productId,
                concentration = enteredCompound.availableAmount
            )

            if (compound == null) {
                // new compound
                val compoundId =
                    DatabaseHandler.addCompound(
                        enteredCompound.copy(
                            productNodes = listOf(newProductNode)
                        )
                    )

                productCompoundNodes.add(
                    CompoundNode(
                        id = compoundId.toInt(),
                        concentration = enteredCompound.availableAmount,
                        available = 1.0
                    )
                )
            } else {
                // compound already exist
                val productNodes = compound.productNodes?.toMutableList()
                productNodes?.add(newProductNode)

                DatabaseHandler.updateCompound(
                    compound.copy(
                        productNodes = productNodes
                    )
                )

                compound.id?.let { id ->
                    productCompoundNodes.add(
                        CompoundNode(
                            id = id,
                            concentration = enteredCompound.availableAmount,
                            available = compound.availableAmount / enteredCompound.availableAmount
                        )
                    )
                }
            }
        }

        return productCompoundNodes
    }

    private suspend fun checkCompoundEntry(
        compoundName: String,
        concentration: String
    ): Boolean {
        if (compoundName.isBlank()) {
            _events.send(
                TextFieldEventState.InvalidInput(ProductTextField.CompoundName)
            )
        }

        if (concentration.isBlank()) {
            _events.send(
                TextFieldEventState.InvalidInput(ProductTextField.Concentration)
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

    internal fun sendAction(action: ProductAction) {
        viewModelScope.launch {
            viewAction.send(action)
        }
    }

    private fun updateState(newState: (ProductScreenViewState) -> ProductScreenViewState) {
        _viewState.update { oldState -> newState(oldState) }
    }

    class Factory(
        private val navigateBack: () -> Unit,
        private val selectedId: Int? = null
    ) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ProductViewModel(
                navigateBack = navigateBack,
                selectedId = selectedId
            ) as T
    }
}
