package com.loanmanagementapp.ui.screen.home

import androidx.lifecycle.ViewModel
import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.utils.LoanCalculatorProvider
import com.loanmanagementapp.utils.PreferenceManagerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferenceManager: PreferenceManagerImpl,
    private val loanCalculatorProvider: LoanCalculatorProvider
) : ViewModel() {

    fun getUserName(): String? {
        return preferenceManager.user?.userName
    }

    fun calculateLoanAmount(loan: Loan): Int {
        return loanCalculatorProvider.calculateLoan(loan)
    }
}