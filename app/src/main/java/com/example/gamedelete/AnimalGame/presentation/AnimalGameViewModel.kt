package com.example.gamedelete.AnimalGame.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AnimalGameViewModel : ViewModel(){

    var state by mutableStateOf(AnimalGameState())
        private set


    init {
        state = state.copy(
            animalList = listOf("Dog", "Frog", "Cat", "Camel")
        )
    }

    fun onTextChanged(newText : String){

        state = state.copy(
            animal = newText
        )

    }

    private var messageJob: Job? = null

    private fun sendMessage(message: String) {
        // Cancel the previous job if it's still active
        messageJob?.cancel()

        // Start a new coroutine for the message
        messageJob = viewModelScope.launch {
            state = state.copy(message = message)

            delay(3000) // Display the message for 3 seconds

            state = state.copy(message = "")
        }
    }


    fun checkInput(){

        if(state.typedAnimalList.contains(state.animal)){
            state = state.copy(
                animal = ""
            )
            sendMessage("Animal already typed!")
            return
        }

        if (state.animalList.contains(state.animal)){

            val mutableList = state.typedAnimalList.toMutableList()
            mutableList.add(state.animal)

            state = state.copy(
                score = state.score+10,
                typedAnimalList = mutableList
            )

            state = state.copy(
                animal = ""
            )
            sendMessage("+10 for you! :D")
        } else{
            sendMessage("Animal not in the list :(")
            state = state.copy(
                animal = ""
            )
        }

    }




}