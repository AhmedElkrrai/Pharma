package com.example.pharmamanufacturer.presentation.products.dialog

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.core.PharmaApp
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import com.example.pharmamanufacturer.data.local.entities.Batch
import com.example.pharmamanufacturer.data.local.entities.CompoundNode
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldErrorEventState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.TextFieldViewState
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.renderFieldTextViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductionDialogViewModel : ViewModel() {

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

        DatabaseHandler.insertBatch(batch)

        val compoundNodes = product.compoundNodes

        val modifiedCompoundNodes = mutableListOf<CompoundNode>()

        for (node in compoundNodes) {
            DatabaseHandler.getCompound(node.id)?.let { compound ->
                val availableAmount = compound.availableAmount - node.concentration
                //update nodes available batches
                Log.d(
                    "TAG", "compound availableAmount = ${compound.availableAmount}," +
                        " availableAmount = $availableAmount" +
                        " concentration = ${node.concentration}"
                )
                modifiedCompoundNodes.add(
                    node.copy(
                        available = availableAmount / node.concentration
                    )
                )

                //update each compound availableAmount
                DatabaseHandler.updateCompound(
                    compound = compound.copy(
                        availableAmount = availableAmount
                    )
                )
            }
        }

        modifiedCompoundNodes.forEach { node ->
            Log.d(
                "TAG", "node available = ${node.available}"
            )
        }

        //update product with nodes updated available batches
        DatabaseHandler.updateProduct(
            product = product.copy(
                compoundNodes = modifiedCompoundNodes
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
