package com.example.pharmamanufacturer.presentation.addproduct

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.presentation.addproduct.action.AddProductAction
import com.example.pharmamanufacturer.presentation.addproduct.state.AddProductTextField
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitleWithIcon
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.styledTextField

@Composable
fun AddProductScreen(navigateBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val viewModel: AddProductViewModel =
            viewModel(factory = AddProductViewModel.Factory(navigateBack))

        val viewState by viewModel.viewState.collectAsStateWithLifecycle()

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        CenteredTitleWithIcon(
            modifier = Modifier.size(30.dp),
            title = "Details",
            painter = painterResource(id = R.drawable.ic_details)
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Medium_Space),
            contentAlignment = Alignment.Center
        ) {
            viewState.name.input = styledTextField(
                label = "Name",
                keyboardType = KeyboardType.Text,
                viewState = viewState.name,
                exitErrorState = {
                    viewModel.sendAction(
                        AddProductAction.RetrieveInitialState(AddProductTextField.Name)
                    )
                },
                showInvalidInput = {
                    viewModel.sendAction(
                        AddProductAction.KEYBOARD(AddProductTextField.Name)
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        CenteredTitleWithIcon(
            modifier = Modifier.size(30.dp),
            title = "Compounds",
            painter = painterResource(id = R.drawable.ic_compound)
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Medium_Space)
        ) {

            viewState.compoundName.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Name",
                keyboardType = KeyboardType.Text,
                viewState = viewState.compoundName,
                exitErrorState = {
                    viewModel.sendAction(
                        AddProductAction.RetrieveInitialState(AddProductTextField.CompoundName)
                    )
                },
                showInvalidInput = {
                    viewModel.sendAction(
                        AddProductAction.KEYBOARD(AddProductTextField.CompoundName)
                    )
                }
            )

            viewState.concentration.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Concentration",
                keyboardType = KeyboardType.Decimal,
                viewState = viewState.concentration,
                exitErrorState = {
                    viewModel.sendAction(
                        AddProductAction.RetrieveInitialState(AddProductTextField.Concentration)
                    )
                },
                showInvalidInput = {
                    viewModel.sendAction(
                        AddProductAction.KEYBOARD(AddProductTextField.Concentration)
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Blue),
                onClick = { viewModel.sendAction(AddProductAction.AddCompound) }
            ) {
                Text(
                    text = "Add Compound",
                    color = Color.White,
                    maxLines = 1
                )
            }
        }

        BottomFloatingButton(
            onClick = {
                viewModel.sendAction(AddProductAction.INSERT)
            }
        )
    }
}
