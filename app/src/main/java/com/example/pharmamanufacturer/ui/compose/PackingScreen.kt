package com.example.pharmamanufacturer.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.ui.compose.theme.ComposeTheme

@Composable
fun PackingScreen() {
    var name by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        TextField(
            value = name,
            onValueChange = {
                name = it
            }
        )

        OutlinedTextField(
            value = name,
            onValueChange = {text ->
                name = text
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PackingScreenPreview() {
    ComposeTheme {
        PackingScreen()
    }
}