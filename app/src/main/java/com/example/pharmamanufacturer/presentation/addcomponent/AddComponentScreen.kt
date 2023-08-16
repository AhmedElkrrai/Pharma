package com.example.pharmamanufacturer.presentation.addcomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType.Companion.Decimal
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.presentation.addcomponent.action.AddComponentAction
import com.example.pharmamanufacturer.presentation.addcomponent.state.AddComponentEventState
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitleWithIcon
import com.example.pharmamanufacturer.presentation.utilitycompose.styledTextField

@Composable
fun AddComponentScreen(navigateBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val viewModel: AddComponentViewModel = viewModel()

        val viewState by viewModel.viewState.collectAsStateWithLifecycle()

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        CenteredTitleWithIcon(
            modifier = Modifier.size(30.dp),
            title = "Details",
            painter = painterResource(id = R.drawable.ic_details)
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Medium_Space)
        ) {
            viewState.name.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Name",
                keyboardType = Text,
                viewState = viewState.name,
                showInvalidInput = {
                    viewModel.sendAction(
                        AddComponentAction.KEYBOARD(
                            invalidInput = AddComponentEventState.InvalidInputState(name = true)
                        )
                    )
                }
            )

            viewState.amount.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Amount",
                keyboardType = Decimal,
                viewState = viewState.amount,
                showInvalidInput = {
                    viewModel.sendAction(
                        AddComponentAction.KEYBOARD(
                            invalidInput = AddComponentEventState.InvalidInputState(amount = true)
                        )
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        CenteredTitleWithIcon(
            modifier = Modifier.size(30.dp),
            title = "Suppliers",
            painter = painterResource(id = R.drawable.ic_doctor)
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Medium_Space)
        ) {

            viewState.supplierName.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Name",
                keyboardType = Text,
                viewState = viewState.supplierName,
                showInvalidInput = {
                    viewModel.sendAction(
                        AddComponentAction.KEYBOARD(
                            invalidInput = AddComponentEventState.InvalidInputState(supplierName = true)
                        )
                    )
                }
            )

            viewState.capacity.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Capacity",
                keyboardType = Decimal,
                viewState = viewState.capacity,
                showInvalidInput = {
                    viewModel.sendAction(
                        AddComponentAction.KEYBOARD(
                            invalidInput = AddComponentEventState.InvalidInputState(capacity = true)
                        )
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Small_Space))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Blue),
                onClick = { viewModel.sendAction(AddComponentAction.AddSupplier) }
            ) {
                Text(
                    text = "Add Supplier",
                    color = Color.White,
                    maxLines = 1
                )
            }
        }

        BottomFloatingButton(
            onClick = {
                viewModel.sendAction(AddComponentAction.INSERT)
                navigateBack.invoke()
            }
        )
    }
}
