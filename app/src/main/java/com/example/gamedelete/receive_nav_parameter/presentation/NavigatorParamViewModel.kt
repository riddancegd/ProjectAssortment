package com.example.gamedelete.receive_nav_parameter.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class NavigatorParamViewModel : ViewModel() {

    private val _state = MutableStateFlow(NavigatorParamState())
    val state = _state.asStateFlow()



    init{

        _state.update {

            it.copy(
                names = listOf(
                    "Jovita",
                    "Filemon",
                    "Cresencio",
                    "Jovita",
                    "Filemon",
                    "Cresencio",
                    "Jovita",
                    "Filemon",
                    "Cresencio",
                    "Jovita",
                    "Filemon",
                    "Cresencio",
                    "Jovita",
                    "Filemon",
                    "Cresencio",
                    "Jovita",
                    "Filemon",
                    "Cresencio"
                )
            )

        }
    }


    fun onAction(action : NavigatorParamActions){

        when(action){
            is NavigatorParamActions.OnFilterValueChange -> onFilterValueChange(action.newValue)
            is NavigatorParamActions.OnNavigateBackPressed -> Unit
        }

    }



    private fun onFilterValueChange(newValue : String){

        _state.update {
            it.copy(
                filterValue = newValue
            )
        }

    }


}