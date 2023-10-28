package com.example.pharmamanufacturer.presentation.products.dialog

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.PharmaApp
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Batch
import com.example.pharmamanufacturer.data.local.entities.MaterialNode
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldErrorEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.renderFieldTextViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductionDialogViewModel @Inject constructor(
    private val db: DatabaseHandler
) : ViewModel() {

    private val viewAction: Channel<ProductionDialogAction> = Channel()

    private val _viewState: MutableStateFlow<TextFieldViewState> =
        MutableStateFlow(TextFieldViewState.initState("Batch"))
    internal val viewState: StateFlow<TextFieldViewState>
        get() = _viewState

    init {
        viewModelScope.launch {
            processActions()
        }
    }

    private suspend fun processActions() {
        viewAction.receiveAsFlow().collect { action ->
            when (action) {
                is ProductionDialogAction.Operate ->
                    operateBatch(action.batch, action.product, action.onDismiss)

                is ProductionDialogAction.INVALID ->
                    renderTextFieldViewState(
                        TextFieldErrorEventState.ENTER
                    )

                is ProductionDialogAction.RetrieveInitialState ->
                    renderTextFieldViewState(
                        TextFieldErrorEventState.EXIT
                    )
            }
        }
    }

    private suspend fun operateBatch(batch: Batch, product: Product, dismissDialog: () -> Unit) {
        if (batch.number.isEmpty()) {
            renderTextFieldViewState(
                TextFieldErrorEventState.ENTER
            )
            return
        }

        if (product.getAvailableBatches() < 1) {
            Toast.makeText(
                PharmaApp.instance.applicationContext,
                "Can not start production, some or all compounds are on low stock",
                Toast.LENGTH_LONG
            )
                .show()

            return
        }

        db.insertBatch(batch)

        val compoundNodes = product.compoundNodes

        val modifiedMaterialNodes = mutableListOf<MaterialNode>()

        for (node in compoundNodes) {
            db.getCompound(node.id)?.let { compound ->
                //update each node available batches
                val availableAmount = compound.availableAmount - node.neededAmount

                modifiedMaterialNodes.add(
                    node.copy(
                        available = availableAmount / node.neededAmount
                    )
                )

                //update each compound availableAmount
                db.updateCompound(
                    compound = compound.copy(
                        availableAmount = availableAmount
                    )
                )
            }
        }

        //update product with nodes updated available batches
        db.updateProduct(
            product = product.copy(
                compoundNodes = modifiedMaterialNodes
            )
        )

        dismissDialog.invoke()
    }

    private fun renderTextFieldViewState(
        errorEventState: TextFieldErrorEventState,
    ) {
        updateState {
            renderFieldTextViewState(
                viewState.value,
                errorEventState
            )
        }
    }

    private fun updateState(newState: (TextFieldViewState) -> TextFieldViewState) {
        _viewState.update { oldState -> newState(oldState) }
    }


    internal fun sendAction(action: ProductionDialogAction) {
        viewModelScope.launch {
            viewAction.send(action)
        }
    }
}
