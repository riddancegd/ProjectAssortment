package com.example.gamedelete.fruitgame.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamedelete.ui.theme.GameDeleteTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


@Composable
fun FruitGameScreen(
    state: FruitGameState,
    onAction: (FruitGameAction) -> Unit,
    events : Flow<FruitGameEvent>
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is FruitGameEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ScoreText(state.score)

        FruitInput(
            fruitName = state.fruitName,
            onValueChange = { onAction(FruitGameAction.OnFruitNameChange(it)) }
        )

        SubmitButton(
            onClick = {onAction(FruitGameAction.SubmitFruit)}
        )

    }

}

@Composable
fun ScoreText(score: Int) {
    Text(
        text = "Score: $score",
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
fun FruitInput(
    fruitName: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = fruitName,
        label = { Text("Fruit") },
        onValueChange = onValueChange,
        singleLine = true,
        modifier = Modifier.testTag("fruitInput")
    )
}

@Composable
fun SubmitButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Submit")
    }
}


@Preview(showSystemUi = true)
@Composable
private fun FruitGamePreview() {

    GameDeleteTheme {

        FruitGameScreen(
            FruitGameState(fruitName = "Apple"),
            onAction = {},
            events = flowOf(),
        )

    }


}