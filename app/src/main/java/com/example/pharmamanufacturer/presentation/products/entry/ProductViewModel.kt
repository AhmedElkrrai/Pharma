package com.example.pharmamanufacturer.presentation.products.entry

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.core.MINIMUM_PRODUCT_INGREDIENTS
import com.example.pharmamanufacturer.core.PRODUCT_ID_KEY
import com.example.pharmamanufacturer.core.PRODUCT_NAME_KEY
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.core.capitalizeFirstChar
import com.example.pharmamanufacturer.data.di.IOContext
import com.example.pharmamanufacturer.data.di.MainContext
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.MaterialNode
import com.example.pharmamanufacturer.data.local.entities.Packaging
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.data.local.entities.ProductNode
import com.example.pharmamanufacturer.presentation.products.entry.action.ProductAction
import com.example.pharmamanufacturer.presentation.products.entry.state.ProductScreenViewState
import com.example.pharmamanufacturer.presentation.products.entry.state.ProductTextField
import com.example.pharmamanufacturer.presentation.products.entry.state.renderViewState
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
class ProductViewModel @Inject constructor(
    @IOContext private val ioContext: CoroutineContext,
    @MainContext private val mainContext: CoroutineContext,
    private val db: DatabaseHandler,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val selectedId: Int? = savedStateHandle.get<Int>(PRODUCT_ID_KEY)
    private val productName: String? = savedStateHandle.get<String>(PRODUCT_NAME_KEY)

    private val viewAction = Channel<ProductAction>()

    private val _viewState = MutableStateFlow(ProductScreenViewState.INIT)
    internal val viewState: StateFlow<ProductScreenViewState>
        get() = _viewState

    private val _events = Channel<TextFieldEventState>()
    private val events: Flow<TextFieldEventState>
        get() = _events.receiveAsFlow()

    private val compounds = mutableListOf<Compound>()
    private val packagingMutableList = mutableListOf<Packaging>()

    init {
        viewModelScope.launch {
            initViewData()
            processActions()
            processEvents()
        }
    }

    private fun initViewData() {
        if (productName != null) {
            updateState {
                it.copy(
                    name = it.name.copy(
                        input = productName
                    )
                )
            }
        }
    }

    private suspend fun processActions() {
        viewModelScope.launch(ioContext) {
            viewAction.receiveAsFlow().collect { action ->
                when (action) {
                    is ProductAction.INSERT -> addProduct(action.navigateBack)

                    is ProductAction.UPDATE -> updateProduct(action.navigateBack)

                    is ProductAction.COMPOUND -> addCompound()

                    is ProductAction.PACKAGING -> addPackaging()

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
                    is TextFieldEventState.InvalidInput ->
                        renderTextFieldViewState(
                            event.textField,
                            TextFieldErrorEventState.ENTER
                        )
                }
            }
        }
    }

    private fun addProduct(navigateBack: () -> Unit) {
        viewModelScope.launch(ioContext) {
            val name = viewState.value.name.input

            if (name.isBlank()) {
                _events.send(
                    TextFieldEventState.InvalidInput(ProductTextField.Name)
                )
                return@launch
            }

            if (compounds.size < MINIMUM_PRODUCT_INGREDIENTS) {
                checkEntry(
                    title = viewState.value.compoundName.input,
                    details = viewState.value.concentration.input,
                    titleTextField = ProductTextField.CompoundName,
                    detailsTextField = ProductTextField.Concentration
                )

                return@launch
            }

            if (packagingMutableList.size < 1) {
                checkEntry(
                    title = viewState.value.packagingType.input,
                    details = viewState.value.packagingAmount.input,
                    titleTextField = ProductTextField.PackagingType,
                    detailsTextField = ProductTextField.PackagingAmount
                )

                return@launch
            }

            val product = Product(
                name = name.capitalizeFirstChar(),
                compoundNodes = listOf(),
                packagingNodes = listOf()
            )

            val productId = db.addProduct(product)

            val compoundNodes = updateCompounds(productId.toInt())
            val packagingListNodes = updatePackagingList(productId.toInt())

            db.updateProduct(
                product = product.copy(
                    id = productId.toInt(),
                    compoundNodes = compoundNodes,
                    packagingNodes = packagingListNodes
                )
            )

            withContext(mainContext) {
                navigateBack.invoke()
            }
        }
    }

    private fun updateProduct(navigateBack: () -> Unit) {
        if (selectedId == null) return

        viewModelScope.launch(ioContext) {
            val name = viewState.value.name.input

            if (name.isBlank()) {
                _events.send(
                    TextFieldEventState.InvalidInput(ProductTextField.Name)
                )
                return@launch
            }

            val product = db.getProduct(selectedId) ?: return@launch

            val newCompoundNodes = updateCompounds(product.id ?: return@launch)
            val updatedCompoundNodes = product.compoundNodes + newCompoundNodes

            val newPackagingNodes = updatePackagingList(product.id)
            val updatedPackagingNodes = product.packagingNodes + newPackagingNodes

            db.updateProduct(
                product = product.copy(
                    name = name.capitalizeFirstChar(),
                    compoundNodes = updatedCompoundNodes,
                    packagingNodes = updatedPackagingNodes
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

        val incompleteEntry = checkEntry(
            title = name,
            details = concentration,
            titleTextField = ProductTextField.CompoundName,
            detailsTextField = ProductTextField.Concentration
        )

        if (incompleteEntry) return

        val compound =
            Compound(
                name = name.capitalizeFirstChar(),
                availableAmount = concentration.toDouble(),
                productNodes = listOf()
            )
        compounds.add(compound)

        clearCompoundInputs()
    }

    private suspend fun updateCompounds(productId: Int): List<MaterialNode> {
        val compoundNodes = mutableListOf<MaterialNode>()

        compounds.forEach { enteredCompound ->
            val compound = db.getCompoundByName(
                name = enteredCompound.name
            )

            val newProductNode = ProductNode(
                id = productId,
                neededAmount = enteredCompound.availableAmount
            )

            if (compound == null) {
                // new compound
                val compoundId =
                    db.addCompound(
                        enteredCompound.copy(
                            productNodes = listOf(newProductNode)
                        )
                    )

                compoundNodes.add(
                    MaterialNode(
                        id = compoundId.toInt(),
                        neededAmount = enteredCompound.availableAmount,
                        available = 1.0
                    )
                )
            } else {
                // compound already exist
                val productNodes = compound.productNodes?.toMutableList()
                productNodes?.add(newProductNode)

                db.updateCompound(
                    compound.copy(
                        productNodes = productNodes
                    )
                )

                compound.id?.let { id ->
                    compoundNodes.add(
                        MaterialNode(
                            id = id,
                            neededAmount = enteredCompound.availableAmount,
                            available = compound.availableAmount / enteredCompound.availableAmount
                        )
                    )
                }
            }
        }

        return compoundNodes
    }

    private suspend fun updatePackagingList(productId: Int): List<MaterialNode> {
        val packagingListNodes = mutableListOf<MaterialNode>()

        packagingMutableList.forEach { enteredPackaging ->
            val packaging = db.getPackagingByType(
                type = enteredPackaging.type
            )

            val newProductNode = ProductNode(
                id = productId,
                neededAmount = enteredPackaging.availableAmount
            )

            if (packaging == null) {
                // new packaging
                val packagingId =
                    db.addPackaging(
                        enteredPackaging.copy(
                            productNodes = listOf(newProductNode)
                        )
                    )

                packagingListNodes.add(
                    MaterialNode(
                        id = packagingId.toInt(),
                        neededAmount = enteredPackaging.availableAmount,
                        available = 1.0
                    )
                )
            } else {
                // packaging already exist
                val productNodes = packaging.productNodes?.toMutableList()
                productNodes?.add(newProductNode)

                db.updatePackaging(
                    packaging.copy(
                        productNodes = productNodes
                    )
                )

                packaging.id?.let { id ->
                    packagingListNodes.add(
                        MaterialNode(
                            id = id,
                            neededAmount = enteredPackaging.availableAmount,
                            available = packaging.availableAmount / enteredPackaging.availableAmount
                        )
                    )
                }
            }
        }

        return packagingListNodes
    }

    private suspend fun addPackaging() {
        val type = viewState.value.packagingType.input
        val neededAmount = viewState.value.packagingAmount.input

        val incompleteEntry = checkEntry(
            title = type,
            details = neededAmount,
            titleTextField = ProductTextField.PackagingType,
            detailsTextField = ProductTextField.PackagingAmount
        )

        if (incompleteEntry) return

        val packaging =
            Packaging(
                type = type.capitalizeFirstChar(),
                availableAmount = neededAmount.toDouble(),
                productNodes = listOf()
            )
        packagingMutableList.add(packaging)

        clearPackagingInputs()
    }

    private suspend fun checkEntry(
        title: String,
        details: String,
        titleTextField: ProductTextField,
        detailsTextField: ProductTextField
    ): Boolean {
        if (title.isBlank()) {
            _events.send(
                TextFieldEventState.InvalidInput(titleTextField)
            )
        }

        if (details.isBlank()) {
            _events.send(
                TextFieldEventState.InvalidInput(detailsTextField)
            )
        }

        return title.isBlank() || details.isBlank()
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

    private fun clearPackagingInputs() {
        updateState {
            it.copy(
                packagingType = it.packagingType.copy(
                    input = TextFieldViewState.CLEARED_FIELD
                ),
                packagingAmount = it.packagingAmount.copy(
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
}
