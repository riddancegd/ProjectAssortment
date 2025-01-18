package com.example.gamedelete.delete_package

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DeleteViewModel : ViewModel() {

    var state by mutableStateOf(DeleteState())
    private set

    fun changeIndex(newIndex: Int){
        state = state.copy(
            selectedIndex = newIndex
        )
    }



}