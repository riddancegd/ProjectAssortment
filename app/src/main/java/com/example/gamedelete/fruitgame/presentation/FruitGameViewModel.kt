package com.example.gamedelete.fruitgame.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamedelete.fruitgame.presentation.data.FruitRepositoryImpl
import com.example.gamedelete.fruitgame.presentation.domain.FruitRepository
import com.example.gamedelete.fruitgame.presentation.domain.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class FruitGameViewModel(
    private val fruitRepository: FruitRepository = FruitRepositoryImpl()
) : ViewModel() {

    var state by mutableStateOf(FruitGameState())
        private set



    private val _events = Channel<FruitGameEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: FruitGameAction) {

        when (action) {
            is FruitGameAction.SubmitFruit -> submitFruit()
            is FruitGameAction.OnFruitNameChange -> setFruitName(action.fruitName)
        }

    }

    private fun setFruitName(fruitName: String) {
        state = state.copy(fruitName = fruitName)
    }

    private fun submitFruit() {

        val result = validateFruit()
        handleResult(result)
        clearFruitName()

    }

    private fun validateFruit(): Result {
        return when {
            state.fruitName.isBlank() -> Result.Error("Type something!")
            state.fruitName in state.typedFruits -> Result.Error("Already in!")
            state.fruitName in fruitRepository.getFruits() -> {
                addFruitAndIncreaseScore()
                Result.Success
            }

            else -> Result.Error("Not in the list!")
        }
    }

    private fun addFruitAndIncreaseScore(){
        state = state.copy(
            score = state.score + 10,
            typedFruits = state.typedFruits + state.fruitName
        )
    }

    private fun handleResult(result : Result){
        val message = if (result is Result.Error) result.message else "+10 pintztz :D"
        sendEvent(message)
    }

    private fun sendEvent(message : String){
        viewModelScope.launch {
            _events.send(FruitGameEvent.ShowToast(message))
        }
    }

    private fun clearFruitName() {
        state = state.copy(fruitName = "")
    }


}