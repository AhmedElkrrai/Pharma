package com.example.pharmamanufacturer.presentation.utilitycompose.textfield

import androidx.compose.ui.graphics.Color
import com.example.pharmamanufacturer.presentation.theme.Blue

data class TextFieldViewState(
    var input: String,
    val hint: String,
    val labelColor: Color,
    val focusedBorderColor: Color,
    val unfocusedBorderColor: Color
) {
    companion object {
        const val CLEARED_FIELD = " "
        const val INVALID_INPUT_HINT = "Invalid Input"
        fun initState(
            hint: String = "",
            initialInput: String = "",
        ): TextFieldViewState {
            return TextFieldViewState(
                input = initialInput,
                hint = hint,
                labelColor = Blue,
                focusedBorderColor = Blue,
                unfocusedBorderColor = Color.LightGray
            )
        }
    }
}
