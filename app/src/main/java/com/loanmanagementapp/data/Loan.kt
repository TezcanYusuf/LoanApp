package com.loanmanagementapp.data

data class Loan(
    val name: String,
    var principalAmount: Double,
    var interestRate: Double,
    var status: LoanStatus,
    var dueIn: Int
)