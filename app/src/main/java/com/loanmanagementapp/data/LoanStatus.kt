package com.loanmanagementapp.data

enum class LoanStatus(val status: String) {
    DEFAULT("default"), ACTIVE("active"), OVERDUE("overdue"), PAID("paid");

    companion object {
        fun find(value: String) = when (value) {
            ACTIVE.status -> ACTIVE
            OVERDUE.status -> OVERDUE
            PAID.status -> PAID
            else -> DEFAULT
        }
    }
}