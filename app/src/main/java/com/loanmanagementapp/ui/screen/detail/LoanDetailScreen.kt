package com.loanmanagementapp.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loanmanagementapp.R
import com.loanmanagementapp.ui.base.BaseText
import com.loanmanagementapp.ui.base.BaseTitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanDetailScreen(
    viewModel: LoanDetailViewModel = hiltViewModel(),
    navController: NavController
) {

    val interestLabel = stringResource(id = R.string.interest)
    val loanManagementLabel = stringResource(id = R.string.loan_detail)

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.offset(y = 16.dp),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        BaseTitleText(loanManagementLabel)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BaseText("$interestLabel: ${viewModel.getLoan(navController)?.interestRate}")
        }
    }
}