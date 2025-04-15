package com.loanmanagementapp.data

import android.content.Context
import com.loanmanagementapp.utils.strategy.processing.LoanProcessingContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LoanRepository @Inject constructor(
    private val loanService: LoanService,
    private val updateContext: LoanProcessingContext
) {
    suspend fun updateLoans(context: Context): List<Loan> {
        val loans = loanService.loadLoans(context)

        val updatedLoans = loans.map { loan ->
            updateContext.applyAll(loan)
        }

        loanService.saveLoans(updatedLoans)
        return updatedLoans
    }
}