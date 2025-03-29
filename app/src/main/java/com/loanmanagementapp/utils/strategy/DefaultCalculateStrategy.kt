package com.loanmanagementapp.utils.strategy

import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.utils.LoanCalculationStrategy
import javax.inject.Inject

class DefaultCalculateStrategy @Inject constructor() : LoanCalculationStrategy {
    override fun calculateLoanAmount(loan: Loan): Int {
        return (loan.principalAmount * loan.interestRate).toInt()
    }
}