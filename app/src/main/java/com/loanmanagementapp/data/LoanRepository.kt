package com.loanmanagementapp.data
import android.content.Context
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LoanRepository @Inject constructor(private val loanService: LoanService) {
    suspend fun updateLoans(context: Context): List<Loan> {
        val loans = loanService.loadLoans(context).toMutableList()
        //todo daha da düzenle interface ve strateji ekle
        for (loan in loans) {
            if (loan.dueIn <= 0) {
                if (loan.loanStatus != LoanStatus.PAID) {
                    loan.loanStatus = if (loan.principalAmount > 0) LoanStatus.OVERDUE else LoanStatus.DEFAULT
                }
            } else if (loan.loanStatus != LoanStatus.DEFAULT) {
                // 2️⃣ Aktif krediler için faiz artır
                loan.interestRate += 0.5
            }

            // 5️⃣ Gün sayısını azalt (En sona alınmalı)
            loan.dueIn -= 1
        }

        loanService.saveLoans(loans)
        return loans
    }
}