package com.example.pharmamanufacturer.presentation.utilitycompose.textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun styledTextField(
    modifier: Modifier = Modifier,
    label: String,
    keyboardType: KeyboardType,
    viewState: TextFieldViewState,
    imeAction: ImeAction = ImeAction.Done,
    exitErrorState: () -> Unit,
    showInvalidInput: () -> Unit
): String {
    var input by remember { mutableStateOf(viewState.input) }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier,
        value = input.takeIf { viewState.input != TextFieldViewState.CLEARED_FIELD }
            ?: run {
                input = ""
                input
            },
        onValueChange = { text ->
            if (input.isBlank())
                exitErrorState()
            input = text
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = viewState.focusedBorderColor,
            unfocusedBorderColor = viewState.unfocusedBorderColor,
            cursorColor = Color.LightGray
        ),
        placeholder = {
            Text(
                modifier = Modifier.alpha(0.5f),
                text = viewState.hint,
                color = Color.LightGray,
            )
        },
        label = {
            Text(
                text = label,
                color = viewState.labelColor
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (input.isBlank())
                    showInvalidInput.invoke()
                else
                    focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        singleLine = true,
        maxLines = 1
    )

    return input
}
