package com.example.gamedelete.fruitgame.presentation

interface FruitGameEvent {

    data class ShowToast(val message : String) : FruitGameEvent

}