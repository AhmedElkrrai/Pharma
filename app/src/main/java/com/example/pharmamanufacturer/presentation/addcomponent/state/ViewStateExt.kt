package com.example.pharmamanufacturer.presentation.addcomponent.state

import com.example.pharmamanufacturer.presentation.theme.Red

fun AddComponentScreenViewState.shouldEnterErrorState(
    invalidInputState: AddComponentEventState.InvalidInputState
): AddComponentScreenViewState {
    return AddComponentScreenViewState(
        name = this.name.enterErrorState(invalidInputState.name),
        amount = this.amount.enterErrorState(invalidInputState.amount),
        supplierName = this.supplierName.enterErrorState(invalidInputState.supplierName),
        capacity = this.capacity.enterErrorState(invalidInputState.capacity)
    )
}

fun FieldTextViewState.enterErrorState(shouldEnterErrorState: Boolean = true): FieldTextViewState {
    return if (shouldEnterErrorState) {
        this.copy(
            hint = FieldTextViewState.INVALID_INPUT_HINT,
            labelColor = Red,
            focusedBorderColor = Red,
            unfocusedBorderColor = Red
        )
    } else this
}