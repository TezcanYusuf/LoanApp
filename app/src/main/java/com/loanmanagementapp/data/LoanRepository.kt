package com.loanmanagementapp.data
import android.content.Context
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LoanRepository @Inject constructor(private val loanService: LoanService) {
    suspend fun updateLoans(context: Context): List<Loan> {
        val loans = loanService.loadLoans(context).toMutableList()

        for (loan in loans) {
            if (loan.dueIn <= 0) {
                if (loan.status != LoanStatus.PAID) {
                    loan.status = if (loan.principalAmount > 0) LoanStatus.OVERDUE else LoanStatus.DEFAULT
                }
            } else {
                // 2️⃣ Aktif krediler için faiz artır
                if (loan.status != LoanStatus.PAID && loan.status != LoanStatus.DEFAULT) {
                    loan.interestRate += 0.5
                }
            }

            // 5️⃣ Gün sayısını azalt (En sona alınmalı)
            loan.dueIn -= 1
        }

        loanService.saveLoans(loans)
        return loans
    }
}
//overdue gelen önce default oluyor sonra tekrar overdue oluyor
//paide eşit olan status tekrar paid olarak setleniyor else'e gerek yok
//overdue olduğunda default setlemek için bir limit var mı bu kontrol edilmeli ödenmemiş borç bazı iflerde defaulta çekiliyor
//kalan bakiye 1000den küçükse paid oluyor bu normal mi görmezden geliniyor
//önce gün eksiltip bir gün eksik hesaplanıyor


/*@ViewModelScoped
class LoanDeletedRepository @Inject constructor(private val loanService: LoanService) {
    suspend fun updateLoans(context: Context): List<Loan> {
        val loans = loanService.loadLoans(context).toMutableList()

        for (i in loans.indices) {
            if (loans[i].status != "paid" && loans[i].status != "default") {
                if (loans[i].dueIn > 0) {
                    loans[i].interestRate += 0.5 // Faiz oranı her gün artıyor
                }
            } else {
                if (loans[i].principalAmount < 1000) {
                    loans[i].status = "paid"
                }
            }

            if (loans[i].status == "overdue" && loans[i].principalAmount > 5000) {
                loans[i].status = "default"
            }

            loans[i].dueIn -= 1

            if (loans[i].dueIn < 0) {
                if (loans[i].status != "paid") {
                    loans[i].status = if (loans[i].principalAmount > 0) "overdue" else "default"
                } else {
                    loans[i].status = "paid"
                }
            }
        }

        loanService.saveLoans(loans)
        return loans
    }
}*/