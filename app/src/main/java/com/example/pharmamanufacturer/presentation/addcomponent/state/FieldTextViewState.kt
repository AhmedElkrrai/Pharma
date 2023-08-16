package com.example.pharmamanufacturer.presentation.addcomponent.state

import androidx.compose.ui.graphics.Color
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.theme.Red

data class FieldTextViewState(
    var input: String,
    val hint: String,
    val labelColor: Color,
    val focusedBorderColor: Color,
    val unfocusedBorderColor: Color
) {
    companion object {
        fun initState(hint: String = ""): FieldTextViewState {
            return FieldTextViewState(
                input = "",
                hint = hint,
                labelColor = Blue,
                focusedBorderColor = Blue,
                unfocusedBorderColor = Color.LightGray
            )
        }

        val INVALID_INPUT: FieldTextViewState by lazy {
            FieldTextViewState(
                input = "",
                hint = "Invalid Input",
                labelColor = Red,
                focusedBorderColor = Red,
                unfocusedBorderColor = Red
            )
        }
    }
}
