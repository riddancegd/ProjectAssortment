package com.example.gamedelete

fun main(){

    checkPass("ddffffffffff")
}



fun checkPass(pass : String){


    val containsDigitsAndLettersOnly = pass.any { !it.isLetterOrDigit() }

    if(containsDigitsAndLettersOnly){
        println("Contains only digits and letters")
    }

    println(pass.any { !it.isLetterOrDigit() })

}