package com.example.pharmamanufacturer.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.data.models.ChemicalComponent
import com.example.pharmamanufacturer.ui.components.ComponentsViewModel
import com.example.pharmamanufacturer.ui.compose.theme.Blue
import com.example.pharmamanufacturer.ui.compose.theme.ComposeTheme
import com.example.pharmamanufacturer.ui.compose.theme.Green

@Composable
fun ComponentsScreen() {
    val vm: ComponentsViewModel = viewModel()
    LazyColumn {
        items(vm.getComponents()) {
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = component.name,
                modifier = Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.titleLarge,
                color = Blue,
            )

            Text(
                text = component.amount.toString(),
                modifier = Modifier.padding(end = 10.dp),
                style = MaterialTheme.typography.titleMedium,
                color = Green
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComponentsScreenPreview() {
    ComposeTheme {
        ComponentsScreen()
    }
}
