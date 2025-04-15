package com.loanmanagementapp.utils.strategy.processing

import com.loanmanagementapp.data.Loan

interface LoanUpdateStrategy {
    fun update(loan: Loan): Loan
}