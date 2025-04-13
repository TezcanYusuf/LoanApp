package com.loanmanagementapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.navigation.NavigationBundleEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoanDetailViewModel @Inject constructor(): ViewModel() {

    fun getLoan(navController: NavController): Loan? {
        return navController.previousBackStackEntry
            ?.savedStateHandle
            ?.get<Loan>(NavigationBundleEnum.LOAN.key)
    }
}