package com.loanmanagementapp.utils

import com.loanmanagementapp.data.Loan

interface LoanCalculationStrategy {
    fun calculateLoanAmount(loan: Loan): Int
}