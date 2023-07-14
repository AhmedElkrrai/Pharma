package com.example.pharmamanufacturer.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.data.models.ChemicalComponent
import com.example.pharmamanufacturer.data.models.componentsList
import com.example.pharmamanufacturer.ui.theme.Blue
import com.example.pharmamanufacturer.ui.theme.ComposeTheme
import com.example.pharmamanufacturer.ui.theme.Green

@Composable
fun ComponentsScreen(components: List<ChemicalComponent>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = androidx.compose.material.MaterialTheme.colors.background
    ) {
        ComposeTheme {
            Column(
                modifier = Modifier.verticalScroll(
                    rememberScrollState()
                )
            ) {
                components.forEach {
                    ComponentItem(it)
                }
            }
        }
    }
}

@Composable
fun ComponentItem(component: ChemicalComponent) {
    Card(
        elevation = 2.dp,
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            ComponentTitle(
                title = component.name,
                modifier = Modifier.weight(0.15f)
            )
            ComponentAmount(
                amount = component.amount.toString(),
                modifier = Modifier.weight(0.15f)
            )
        }
    }
}

@Composable
fun ComponentTitle(
    title: String,
    modifier: Modifier
) {
    Text(
        text = title,
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        color = Blue,
    )
}

@Composable
fun ComponentAmount(
    amount: String,
    modifier: Modifier
) {
    Text(
        text = amount,
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium,
        color = Green,
        textAlign = TextAlign.End
    )
}

@Preview(showBackground = true)
@Composable
fun ComponentsScreenPreview() {
    ComposeTheme {
        ComponentsScreen(componentsList)
    }
}
