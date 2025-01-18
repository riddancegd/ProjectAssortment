package com.example.gamedelete.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.gamedelete.core.presentation.HomeScreen
import com.example.gamedelete.fruitgame.presentation.FruitGameScreen
import com.example.gamedelete.fruitgame.presentation.FruitGameViewModel
import com.example.gamedelete.onboarding.presentation.OnboardingScreen
import com.example.gamedelete.receive_nav_parameter.presentation.NavigationParamScreen
import com.example.gamedelete.receive_nav_parameter.presentation.NavigatorParamActions
import com.example.gamedelete.receive_nav_parameter.presentation.NavigatorParamViewModel

@Composable
fun NavigationWrapper(
    viewModel: NavigationViewModel = hiltViewModel(),
    fruitGameViewModel: FruitGameViewModel = hiltViewModel(),
    navigationParamViewModel: NavigatorParamViewModel = hiltViewModel()
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = viewModel.startDestination) {

        composable<Destination.Onboarding> {
            OnboardingScreen {
                navController.navigate(Destination.Home) {
                    popUpTo<Destination.Home> { inclusive = true }
                }
            }
        }

        composable<Destination.Home> {
            HomeScreen(
                navigateToOnboarding = {
                    navController.navigate(Destination.Onboarding) {
                        popUpTo<Destination.Onboarding> { inclusive = true }
                    }
                },
                navigateToFruitGame = { navController.navigate(Destination.FruitGame) {} },
                navigateToParams = { navController.navigate(Destination.NavigationParam(it)) }
            )
        }

        composable<Destination.FruitGame> {

            FruitGameScreen(
                state = fruitGameViewModel.state,
                onAction = fruitGameViewModel::onAction,
                events = fruitGameViewModel.events,
            )
        }

        composable<Destination.NavigationParam> {

            val param = it.toRoute<Destination.NavigationParam>()

            // Collect the state from the ViewModel
            val state by navigationParamViewModel.state.collectAsState()

            NavigationParamScreen(
                param = param.param,
                state = state,
                onAction = { action ->
                    when (action) {
                        is NavigatorParamActions.OnNavigateBackPressed -> {
                            navController.popBackStack()
                        }

                        else -> Unit
                    }
                    navigationParamViewModel.onAction(action)
                }
            )
        }

    }

}