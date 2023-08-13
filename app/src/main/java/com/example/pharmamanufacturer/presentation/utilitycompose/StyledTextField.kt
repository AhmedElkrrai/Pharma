package com.example.pharmamanufacturer.presentation.utilitycompose

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

@Composable
fun styledTextField(
    modifier: Modifier = Modifier,
    placeHolderText: String,
    label: String,
    keyboardType: KeyboardType,
    clearInput: Boolean = false
): String {
    var input by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    if (clearInput) input = ""

    OutlinedTextField(
        modifier = modifier,
        value = input,
        onValueChange = { text -> input = text },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Blue,
            cursorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                modifier = Modifier.alpha(0.5f),
                text = placeHolderText,
                color = Color.LightGray,
            )
        },
        label = {
            Text(
                text = label,
                color = Blue
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        singleLine = true,
        maxLines = 1
    )
    return input
}
