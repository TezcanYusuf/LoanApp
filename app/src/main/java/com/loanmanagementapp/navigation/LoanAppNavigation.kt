package com.loanmanagementapp.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
 import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.loanmanagementapp.ui.MainActivityViewModel
import com.loanmanagementapp.ui.screen.detail.LoanDetailScreen
import com.loanmanagementapp.ui.screen.home.HomeScreen
import com.loanmanagementapp.ui.screen.login.LoginScreen

@Composable
fun LoanApp(viewModel: MainActivityViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Sadece LOGIN ve HOME ekranlarında geri tuşunu devre dışı bırak
    if (currentRoute == NavigationEnum.LOGIN.screen || currentRoute == NavigationEnum.HOME.screen) {
        BackHandler(enabled = true) {
            // Geri tuşunu engelle
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) NavigationEnum.HOME.screen else NavigationEnum.LOGIN.screen
    ) {
        composable(NavigationEnum.LOGIN.screen) {
            LoginScreen(onLoginSuccess = {
                viewModel.login()
                navController.navigate(NavigationEnum.HOME.screen)
            })
        }
        composable(NavigationEnum.HOME.screen) {
            HomeScreen(navController = navController)
        }

        composable(NavigationEnum.DETAIL.screen) {
            LoanDetailScreen(navController = navController)
        }
    }
}