package com.loanmanagementapp.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
 import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loanmanagementapp.ui.MainActivityViewModel
import com.loanmanagementapp.ui.screen.home.HomeScreen
import com.loanmanagementapp.ui.screen.login.LoginScreen

@Composable
fun LoanApp(viewModel: MainActivityViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) NavigationEnum.HOME.screen else NavigationEnum.LOGIN.screen
    ) {
        composable(NavigationEnum.LOGIN.screen) {
            BackHandler(enabled = true) {
                // Disable back press
            }
            LoginScreen(onLoginSuccess = {
                viewModel.login()
                navController.navigate(NavigationEnum.HOME.screen)
            })
        }
        composable(NavigationEnum.HOME.screen) {
            BackHandler(enabled = true) {
                // Disable back press
            }
            HomeScreen(navController = navController)
        }
    }
}