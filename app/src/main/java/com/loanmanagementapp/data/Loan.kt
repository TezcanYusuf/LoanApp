package com.loanmanagementapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Loan(
    val name: String,
    var principalAmount: Double,
    var interestRate: Double,
    var loanStatus: LoanStatus,
    var dueIn: Int
): Parcelable