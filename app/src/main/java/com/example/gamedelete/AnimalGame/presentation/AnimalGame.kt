package com.example.gamedelete.AnimalGame.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimalGameRoot(animalGameViewModel: AnimalGameViewModel) {

    AnimalGame(
        animalGameViewModel.state,
        animalGameViewModel::onTextChanged,
        animalGameViewModel::checkInput
    )

}

@Composable
fun AnimalGame(
    state : AnimalGameState,
    onTextChanged : (String) -> Unit,
    onTextInput : () -> Unit
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)) {

        Text(text = "Score: ${state.score}")

        TextField(value = state.animal, onValueChange = onTextChanged, placeholder = { Text("Input an animal") })

        Button(onClick = onTextInput) {
            Text("Input")
        }

        Text(text = state.message, color = Color.Blue)


    }

}


@Preview
@Composable
private fun AnimalGamePreview() {
    AnimalGame(
        state = AnimalGameState(
            animal = "",
            score = 0,
            animalList = emptyList(),
            typedAnimalList = emptyList(),
            message = "ola k ase"
        ),
        onTextChanged = {},
        onTextInput = {}
    )
}