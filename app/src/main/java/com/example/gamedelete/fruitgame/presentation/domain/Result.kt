package com.example.gamedelete.fruitgame.presentation.domain

interface Result {

    data object Success : Result
    data class Error(val message : String)  : Result

}