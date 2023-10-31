package com.example.fizzbuzz.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fizzbuzz.domain.usecases.FizzBuzzUseCase
import com.example.fizzbuzz.presentation.ui.FormScreen
import com.example.fizzbuzz.presentation.ui.ListScreen
import com.example.fizzbuzz.presentation.viewmodels.ViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.FORM.name
) {
    val viewModel = ViewModel(FizzBuzzUseCase())

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.FORM.name) {
            FormScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }
        composable(Screen.RESULTS.name) {
            ListScreen(
                viewModel = viewModel,
            )
        }
    }
}