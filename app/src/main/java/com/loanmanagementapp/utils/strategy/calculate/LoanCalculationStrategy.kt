package com.loanmanagementapp.utils.strategy.calculate

import com.loanmanagementapp.data.Loan

interface LoanCalculationStrategy {
    fun calculateLoanAmount(loan: Loan): Int
}