package com.loanmanagementapp.utils.strategy.calculate

import com.loanmanagementapp.data.Loan
import javax.inject.Inject

class DefaultCalculateStrategy @Inject constructor() : LoanCalculationStrategy {
    override fun calculateLoanAmount(loan: Loan): Int {
        return (loan.principalAmount * loan.interestRate).toInt()
    }
}