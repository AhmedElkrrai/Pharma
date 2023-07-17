package com.example.pharmamanufacturer.ui.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import com.example.pharmamanufacturer.ui.compose.theme.Blue
import com.example.pharmamanufacturer.ui.compose.theme.ComposeTheme
import com.example.pharmamanufacturer.ui.compose.theme.Green

@Composable
fun ComponentsScreen(components: List<ChemicalComponent>) {
    LazyColumn {
        items(components) {
            ComponentItem(it)
        }
    }
}

@Composable
fun ComponentItem(
    component: ChemicalComponent,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            ComponentTitle(
                title = component.name,
                modifier = Modifier.weight(0.6f)
            )
            //Spacer(modifier = Modifier.width(30.dp))
            ComponentAmount(
                amount = component.amount.toString(),
                modifier = Modifier.weight(0.4f)
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
        modifier = modifier.padding(start = 10.dp),
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
        modifier = modifier.padding(end = 10.dp),
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
