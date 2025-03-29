package com.loanmanagementapp.ui.screen.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.data.LoanRepository
import com.loanmanagementapp.utils.LoanCalculatorProvider
import com.loanmanagementapp.utils.PreferenceManagerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferenceManager: PreferenceManagerImpl,
    private val loanCalculatorProvider: LoanCalculatorProvider,
    private val loanRepository: LoanRepository
) : ViewModel() {

    fun getUserName(): String? {
        return preferenceManager.user?.userName
    }

    fun calculateLoanAmount(loan: Loan): Int {
        return loanCalculatorProvider.calculateLoan(loan)
    }

    suspend fun updateLoans(context: Context): List<Loan> {
        return loanRepository.updateLoans(context)
    }

    fun logout() {
        preferenceManager.user = null
    }
}