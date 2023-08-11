package com.example.pharmamanufacturer.presentation.componentdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.presentation.theme.Blue

@Composable
fun ComponentDetailsScreen(onBackClick: () -> Unit) {
    val vm: ComponentDetailsViewModel = viewModel()
    Column(modifier = Modifier.fillMaxSize()) {
        vm.selectedComponent?.let { component ->
            TopBar(
                name = component.name,
                modifier = Modifier
                    .fillMaxWidth(),
                onBackClick = onBackClick
            )
        }
    }
}

@Composable
fun TopBar(
    name: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier
                .size(28.dp)
                .padding(start = 6.dp)
                .clickable {
                    onBackClick.invoke()
                }
        )

        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Blue,
            )
        }
    }
}

/*@Composable
fun ComponentDetails(component: ChemicalComponent) {
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
}*/
