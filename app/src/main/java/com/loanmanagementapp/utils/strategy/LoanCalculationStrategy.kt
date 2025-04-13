package com.loanmanagementapp.utils.strategy

import com.loanmanagementapp.data.Loan

interface LoanCalculationStrategy {
    fun calculateLoanAmount(loan: Loan): Int
}