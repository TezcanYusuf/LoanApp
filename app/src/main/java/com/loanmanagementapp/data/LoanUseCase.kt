package com.loanmanagementapp.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoanUpdateUseCase @Inject constructor(
    private val loanRepository: LoanRepository
    //preferenceManager: PreferenceManagerImpl yazmak istiyorsan buraya ekle
) {
    suspend fun execute(context: Context): Flow<List<Loan>> = flow {
        try {
            val updatedLoans = loanRepository.updateLoans(context)
            emit(updatedLoans)
        } catch (exception: Exception) {
            emit(emptyList())
        }
    }
}