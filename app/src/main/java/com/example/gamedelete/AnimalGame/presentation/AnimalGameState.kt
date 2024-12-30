package com.example.gamedelete.AnimalGame.presentation

data class AnimalGameState(

    val animal : String = "",
    val score : Int = 0,
    val animalList : List<String> = emptyList(),
    val typedAnimalList : List<String> = emptyList(),
    val message : String = ""

)
