package com.example.pharmamanufacturer.presentation.products.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.data.local.entities.Batch
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.theme.Green
import com.example.pharmamanufacturer.presentation.theme.Red
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.styledTextField

@Composable
fun ProductionDialog(
    modifier: Modifier = Modifier,
    product: Product,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            elevation = UiDimensions.Small_Space,
            shape = RoundedCornerShape(15.dp),
            modifier = modifier
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val viewModel: ProductionDialogViewModel = viewModel()

                val viewState by viewModel.viewState.collectAsStateWithLifecycle()

                Spacer(modifier = Modifier.height(UiDimensions.Large_Space))

                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = Blue
                )

                Spacer(modifier = Modifier.height(UiDimensions.Large_Space))

                viewState.input = styledTextField(
                    label = "Batch",
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Go,
                    viewState = viewState,
                    exitErrorState = {
                        viewModel.sendAction(
                            ProductionDialogAction.RetrieveInitialState
                        )
                    },
                    showInvalidInput = {
                        ProductionDialogAction.INVALID
                    }
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 40.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        ProductionDialogButton(
                            borderColor = Red,
                            buttonMessage = "Cancel",
                            onClick = { onDismiss.invoke() }
                        )

                        ProductionDialogButton(
                            borderColor = Green,
                            buttonMessage = "Operate",
                            onClick = {
                                viewModel.sendAction(
                                    ProductionDialogAction.Operate(
                                        batch = Batch(
                                            productId = product.id ?: return@ProductionDialogButton,
                                            number = viewState.input
                                        ),
                                        product = product,
                                        onDismiss = onDismiss
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
