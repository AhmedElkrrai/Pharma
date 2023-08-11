package com.example.pharmamanufacturer.presentation.utilityscreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.presentation.theme.AquaBlue

@Composable
fun EmptyContentScreen(
    message: String,
    animationResource: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.75f),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(animationResource)
        )
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(UiDimensions.Medium_Padding),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = message,
            color = AquaBlue
        )
    }
}