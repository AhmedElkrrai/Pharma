package com.example.pharmamanufacturer.presentation.componentdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.presentation.theme.DarkRed
import com.example.pharmamanufacturer.presentation.theme.DeepBlue

@Composable
fun ComponentDetailsScreen() {
    val vm: ComponentDetailsViewModel = viewModel()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        vm.selectedComponent?.let { component ->
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = DeepBlue,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Name")
                    }

                    append(" = ${component.name}\n")

                    withStyle(
                        style = SpanStyle(
                            color = DarkRed,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append("Amount")
                    }

                    append(" = ${component.amount}")
                },
                fontStyle = FontStyle.Italic
            )
        }
    }
}
