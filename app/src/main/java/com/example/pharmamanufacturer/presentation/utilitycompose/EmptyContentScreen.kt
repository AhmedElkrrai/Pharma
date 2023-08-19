package com.example.pharmamanufacturer.presentation.utilitycompose

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
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.presentation.theme.AquaBlue

@Composable
fun EmptyContentScreen(
    modifier: Modifier = Modifier,
    message: String,
    animationResource: Int
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(animationResource)
        )
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
    }

    Box(
        modifier = modifier
            .padding(UiDimensions.Medium_Space),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = message,
            color = AquaBlue
        )
    }
}