package com.example.pharmamanufacturer.data.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.pharmamanufacturer.ui.compose.theme.AquaBlue
import com.example.pharmamanufacturer.ui.compose.theme.ButtonBlue

data class BottomMenuItem(
    val title: String,
    @DrawableRes val iconId: Int,
    val activeHighlightColor: Color = ButtonBlue,
    val activeColor: Color = Color.White,
    val inactiveColor: Color = AquaBlue
)