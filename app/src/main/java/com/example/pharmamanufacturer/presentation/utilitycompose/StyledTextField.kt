package com.example.pharmamanufacturer.presentation.utilitycompose

import android.util.Log
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.theme.Red

private const val INVALID_INPUT_MESSAGE = "Invalid Input"

@Composable
fun styledTextField(
    modifier: Modifier = Modifier,
    placeHolderText: String,
    label: String,
    keyboardType: KeyboardType,
    fieldColor: Color = Blue,
    invalidInput: Boolean = false,
    clearInput: Boolean = false
): String {
    var input by remember { mutableStateOf("") }
    var placeHolder by remember { mutableStateOf(placeHolderText) }
    var color by remember { mutableStateOf(fieldColor) }

    val focusManager = LocalFocusManager.current

    if (clearInput) input = ""

    if (invalidInput) {
        color = Red
        placeHolder = INVALID_INPUT_MESSAGE
    } else {
        color = Blue
        placeHolder = placeHolderText
    }

    Log.d("taggs", "invalidInput = $invalidInput, color = $color")

    OutlinedTextField(
        modifier = modifier,
        value = input,
        onValueChange = { text ->
            color = Blue
            input = text
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = color,
            unfocusedBorderColor = if (invalidInput) Red else Color.LightGray,
            cursorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                modifier = Modifier.alpha(0.5f),
                text = placeHolder,
                color = Color.LightGray,
            )
        },
        label = {
            Text(
                text = label,
                color = color
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (input.isBlank()) {
                    color = Red
                    placeHolder = INVALID_INPUT_MESSAGE
                } else
                    focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        singleLine = true,
        maxLines = 1
    )
    return input
}
