package com.example.pharmamanufacturer.presentation.products.dialog

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.core.PharmaApp
import com.example.pharmamanufacturer.data.local.entities.Batch
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

    private val viewAction = Channel<ProductionDialogAction>()

    private val _viewState = MutableStateFlow(TextFieldViewState.initState("Batch"))
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
                    operateBatch(action.batch)

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

    private fun operateBatch(batch: Batch) {
        if (batch.number.isEmpty()){
            renderTextFieldViewState(
                TextFieldErrorEventState.ENTER
            )
            return
        }

        Toast.makeText(
            PharmaApp.instance.applicationContext,
            batch.number,
            Toast.LENGTH_LONG
        )
            .show()
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
