package com.loanmanagementapp.ui.screen.home.adapter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.ui.base.BaseText
import com.loanmanagementapp.ui.screen.home.HomeViewModel

@Composable
fun LoanItem(
    loan: Loan, viewModel: HomeViewModel, interestLabel: String, loanLabel: String,
    onClick: (Loan) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .clickable { onClick(loan) }
            .padding(16.dp)
    ) {
        BaseText(loan.name)
        BaseText(loan.loanStatus.status)
        BaseText("$interestLabel: ${loan.interestRate}")
        BaseText("$loanLabel: ${viewModel.calculateLoanAmount(loan)}")
    }
}