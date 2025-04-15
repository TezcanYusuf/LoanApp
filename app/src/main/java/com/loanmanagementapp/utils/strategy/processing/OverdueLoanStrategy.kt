package com.loanmanagementapp.utils.strategy.processing

import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.data.LoanStatus

class OverdueLoanStrategy : LoanUpdateStrategy {
    override fun update(loan: Loan): Loan {
        if (loan.dueIn <= 0 && loan.loanStatus != LoanStatus.PAID) {
            loan.loanStatus = if (loan.principalAmount > 0) LoanStatus.OVERDUE else LoanStatus.DEFAULT
        }
        return loan
    }
}