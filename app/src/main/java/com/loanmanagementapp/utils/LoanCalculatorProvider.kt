package com.loanmanagementapp.utils

import com.loanmanagementapp.data.Loan
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoanCalculatorProvider @Inject constructor() {

    fun calculateLoan(loan: Loan): Int {
        return (loan.principalAmount * loan.interestRate).toInt()
    }
}