package com.loanmanagementapp.utils

import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.utils.strategy.DefaultCalculateStrategy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoanCalculatorProvider @Inject constructor(
    private val defaultCalculateStrategy: DefaultCalculateStrategy
) {

    fun calculateLoan(loan: Loan): Int {
        return defaultCalculateStrategy.calculateLoanAmount(loan)
    }
}