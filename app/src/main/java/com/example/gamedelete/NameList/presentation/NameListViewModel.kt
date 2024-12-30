package com.example.gamedelete.NameList.presentation

import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class NameListViewModel : ViewModel() {

    var state by mutableStateOf(NameState())
        private set


    init {
        state = state.copy(
            names = listOf("Joe", "John", "Mary")
        )
    }



    fun addName(){

        val mutableNames = state.names.toMutableList()
        mutableNames.add(state.name)
        state = state.copy(
          names = mutableNames
        )

    }

    fun deleteName(name: String){

        val mutableNames = state.names.toMutableList()
        mutableNames -= name

        state = state.copy(
            names = mutableNames
        )

    }

    fun onNameChanged(newName : String){

        state = state.copy(name = newName)

    }



}