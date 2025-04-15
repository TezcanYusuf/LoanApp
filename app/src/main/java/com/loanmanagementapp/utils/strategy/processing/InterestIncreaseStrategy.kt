package com.loanmanagementapp.utils.strategy.processing

import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.data.LoanStatus

class InterestIncreaseStrategy : LoanUpdateStrategy {
    override fun update(loan: Loan): Loan {
        if (loan.dueIn > 0 && loan.loanStatus != LoanStatus.DEFAULT) {
            loan.interestRate += 0.5
        }
        return loan
    }
}
