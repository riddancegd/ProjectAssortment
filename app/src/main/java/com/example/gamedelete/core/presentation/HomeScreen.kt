package com.example.gamedelete.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamedelete.ui.theme.GameDeleteTheme


@Composable
fun HomeScreen(
    navigateToFruitGame: () -> Unit,
    navigateToOnboarding: () -> Unit,
    navigateToParams : (String) -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp) // Agrega padding general si lo necesitas
    ) {
        // Contenido centrado
        Column(
            Modifier
                .align(Alignment.Center), // Centra esta columna en la pantalla
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = navigateToOnboarding
            ) { Text("Onboarding") }

            Button(
                onClick = navigateToFruitGame
            ) { Text("FruitGame") }
        }

        // Botón al final
        Button(
            onClick = navigateToFruitGame,
            Modifier
                .align(Alignment.BottomCenter) // Coloca este botón en la parte inferior
                .padding(bottom = 30.dp) // Espaciado desde el borde inferior
        ) { Text("ola") }


        Button(
            onClick = { navigateToParams("ola k ase") },
            Modifier
                .align(Alignment.BottomEnd) // Coloca este botón en la parte inferior
                .padding(bottom = 30.dp) // Espaciado desde el borde inferior
        ) { Text("PARAMz") }


    }
}

@Preview
@Composable
private fun HomeScreenPreview() {

    GameDeleteTheme {
        HomeScreen({}, {}, {})
    }

}