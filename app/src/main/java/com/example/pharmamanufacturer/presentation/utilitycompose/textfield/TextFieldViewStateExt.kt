package com.example.pharmamanufacturer.presentation.utilitycompose.textfield

import androidx.compose.ui.graphics.Color
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.theme.Red

fun renderFieldTextViewState(
    textFieldViewState: TextFieldViewState,
    eventState: TextFieldErrorEventState
): TextFieldViewState {
    return when (eventState) {
        TextFieldErrorEventState.ENTER -> textFieldViewState.enterErrorState()
        TextFieldErrorEventState.EXIT -> textFieldViewState.exitErrorState()
    }
}

fun TextFieldViewState.enterErrorState(): TextFieldViewState {
    return this.copy(
        hint = TextFieldViewState.INVALID_INPUT_HINT,
        labelColor = Red,
        focusedBorderColor = Red,
        unfocusedBorderColor = Red
    )
}

fun TextFieldViewState.exitErrorState(): TextFieldViewState {
    return this.copy(
        hint = "",
        labelColor = Blue,
        focusedBorderColor = Blue,
        unfocusedBorderColor = Color.LightGray
    )
}
