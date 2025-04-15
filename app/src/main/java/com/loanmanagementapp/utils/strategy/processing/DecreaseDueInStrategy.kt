package com.loanmanagementapp.utils.strategy.processing

import com.loanmanagementapp.data.Loan

class DecreaseDueInStrategy : LoanUpdateStrategy {
    override fun update(loan: Loan): Loan {
        loan.dueIn -= 1
        return loan
    }
}