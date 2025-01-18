package com.example.gamedelete.fruitgame.presentation

import androidx.compose.runtime.Immutable


@Immutable
data class FruitGameState(
    val fruitName : String = "",
    val score : Int = 0,
    val typedFruits : List<String> = emptyList()
)
