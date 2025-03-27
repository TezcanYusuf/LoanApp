package com.loanmanagementapp.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.loanmanagementapp.data.LoanRepository
import com.loanmanagementapp.ui.screen.home.HomeScreen
import com.loanmanagementapp.ui.screen.login.LoginScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var loanRepository: LoanRepository
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           LoanApp(loanRepository)
        }
    }
}

@Composable
fun LoanApp(repository: LoanRepository, viewModel: MainActivityViewModel = hiltViewModel()) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    if (isLoggedIn) {
        HomeScreen(repository)
    } else {
        LoginScreen(onLoginSuccess = { viewModel.login() })
    }
}
