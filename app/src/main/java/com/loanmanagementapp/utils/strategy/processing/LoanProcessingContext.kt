package com.loanmanagementapp.utils.strategy.processing

import com.loanmanagementapp.data.Loan
import javax.inject.Inject

class LoanProcessingContext @Inject constructor(
    private val strategies: Set<@JvmSuppressWildcards LoanUpdateStrategy>
) {
    fun applyAll(loan: Loan): Loan {
        return strategies.fold(loan) { updatedLoan, strategy ->
            strategy.update(updatedLoan)
        }
    }
}