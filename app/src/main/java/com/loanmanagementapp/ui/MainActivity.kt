package com.loanmanagementapp.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.loanmanagementapp.navigation.LoanApp
import com.loanmanagementapp.ui.theme.LoanManagementAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoanManagementAppTheme {
                LoanApp()
            }
        }
    }
}