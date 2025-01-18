package com.example.gamedelete.fruitgame.presentation

sealed interface FruitGameAction {

    data class OnFruitNameChange(val fruitName : String) : FruitGameAction
    data object SubmitFruit : FruitGameAction

}