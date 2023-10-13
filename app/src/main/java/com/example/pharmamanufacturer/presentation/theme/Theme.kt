package com.example.pharmamanufacturer.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorPalette = lightColors(
    primary = DeepBlue,
    secondary = Red,
    background = Color.White
)

private val DarkColorPalette = darkColors(
    primary = DeepBlue,
    secondary = Secondary,
    background = Background,
    surface = Surface
)

@Composable
fun PharmaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}