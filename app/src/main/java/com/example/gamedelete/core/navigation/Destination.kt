package com.example.gamedelete.core.navigation

import kotlinx.serialization.Serializable
import java.io.Serial


sealed interface Destination{

    @Serializable
    data object Onboarding : Destination
    @Serializable
    data object Home : Destination
    @Serializable
    data object FruitGame : Destination
    @Serializable
    data class NavigationParam(val param: String) : Destination

}