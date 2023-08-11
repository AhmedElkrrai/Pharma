package com.example.pharmamanufacturer.presentation.componentdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun StyledText(
    modifier: Modifier = Modifier,
    amount: String,
    unit: String,
    amountColor: Color,
    unitColor: Color
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = amountColor,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(amount)
            }

            withStyle(
                style = SpanStyle(
                    color = unitColor,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(unit)
            }
        }
    )
}
