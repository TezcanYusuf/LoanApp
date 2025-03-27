package com.loanmanagementapp.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.data.LoanRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(repository: LoanRepository, viewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var loans by remember { mutableStateOf(emptyList<Loan>()) }
    val scrollState = rememberScrollState() // ⬅ Kaydırma Durumu
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = { TopAppBar(title = { Text("Loan Management") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "User Name: ${viewModel.getUserName()}", fontSize = 16.sp)
            Button(onClick = {
                if (loans.isEmpty())
                    coroutineScope.launch {
                        loans = repository.updateLoans(context)
                    }
                else
                    loans = emptyList()
            }) {
                Text("Load Loans", fontSize = 16.sp)
            }
            loans.forEach { loan ->
                Text(
                    "${loan.name} - ${loan.status}\n Interest: ${loan.interestRate}%\n Loan: ${
                        viewModel.calculateLoanAmount(loan)
                    }\n\n",
                    fontSize = 16.sp
                )
            }
        }
    }
}