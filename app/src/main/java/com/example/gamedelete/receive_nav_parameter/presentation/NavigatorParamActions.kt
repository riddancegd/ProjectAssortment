package com.example.gamedelete.receive_nav_parameter.presentation

sealed interface NavigatorParamActions {

    data class OnFilterValueChange(val newValue : String) : NavigatorParamActions
    data object OnNavigateBackPressed : NavigatorParamActions

}