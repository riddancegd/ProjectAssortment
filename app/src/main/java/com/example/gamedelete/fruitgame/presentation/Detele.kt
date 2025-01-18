package com.example.gamedelete.fruitgame.presentation

import com.example.gamedelete.fruitgame.presentation.domain.Result


fun main(){

    val result : Result = Result.Error("malo malo")

    if(result is Result.Success){
        println("Chilo")
    }else{
        println("No todo chilo")
    }



}