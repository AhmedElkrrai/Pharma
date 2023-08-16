package com.example.pharmamanufacturer.presentation.addcomponent.state

import androidx.compose.ui.graphics.Color
import com.example.pharmamanufacturer.presentation.theme.Blue

data class FieldTextViewState(
    var input: String,
    val hint: String,
    val labelColor: Color,
    val focusedBorderColor: Color,
    val unfocusedBorderColor: Color
) {
    companion object {
        const val CLEARED_FIELD = " "
        const val INVALID_INPUT_HINT = "Invalid Input"
        fun initState(hint: String = ""): FieldTextViewState {
            return FieldTextViewState(
                input = "",
                hint = hint,
                labelColor = Blue,
                focusedBorderColor = Blue,
                unfocusedBorderColor = Color.LightGray
            )
        }
    }
}
