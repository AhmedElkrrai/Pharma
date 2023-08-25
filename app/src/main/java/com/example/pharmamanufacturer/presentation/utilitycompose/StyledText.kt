package com.example.pharmamanufacturer.presentation.utilitycompose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.example.pharmamanufacturer.presentation.theme.Green

@Composable
fun StyledText(
    modifier: Modifier = Modifier,
    details: String,
    unit: String = " KG",
    detailsColor: Color = Green,
    unitColor: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = detailsColor,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(details)
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
