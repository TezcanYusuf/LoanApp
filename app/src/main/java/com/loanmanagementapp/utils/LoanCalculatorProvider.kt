package com.loanmanagementapp.utils

import com.loanmanagementapp.data.Loan
import javax.inject.Singleton

@Singleton
class LoanCalculatorProvider {

    fun calculateLoan(loan: Loan): Int {
        return (loan.principalAmount * loan.interestRate).toInt()
    }
}