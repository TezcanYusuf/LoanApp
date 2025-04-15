package com.loanmanagementapp.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.loanmanagementapp.R
import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.navigation.NavigationBundleEnum
import com.loanmanagementapp.navigation.NavigationEnum
import com.loanmanagementapp.ui.base.BaseText
import com.loanmanagementapp.ui.base.BaseTitleText
import com.loanmanagementapp.ui.base.LogoutButton
import com.loanmanagementapp.ui.screen.home.adapter.LoanItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val loans by viewModel.loans.collectAsState(initial = emptyList())

    val loanManagementLabel = stringResource(id = R.string.loan_management)
    val loadLoansLabel = stringResource(id = R.string.load_loans)
    val hideLoansLabel = stringResource(id = R.string.hide_loans)
    val userNameLabel = stringResource(id = R.string.user_name)
    val interestLabel = stringResource(id = R.string.interest)
    val loanLabel = stringResource(id = R.string.loan)
    val logoutLabel = stringResource(id = R.string.logout)

    val buttonLabel by remember {
        derivedStateOf { if (loans.isEmpty()) loadLoansLabel else hideLoansLabel }
    } //gereksiz recomposition'ı önlemek için

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
                },
                actions = {
                    LogoutButton(
                        onLogoutClick = {
                            viewModel.logout()
                            navController.navigate(NavigationEnum.LOGIN.screen) {
                                popUpTo(NavigationEnum.HOME.screen) { inclusive = true }
                            }
                        },
                        label = logoutLabel
                    )
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
            BaseText(
                text = "$userNameLabel: ${viewModel.getUserName()}",
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                coroutineScope.launch {
                    viewModel.updateLoans(context)
                }
            }) {
                Text(buttonLabel, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (loans.isNotEmpty()) {
                LazyColumn(//todo bir itema tıklayınca detay sayfasına git detay sayfasına text koyman yetiyor
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(loans) { loan ->
                        LoanItem(
                            loan = loan,
                            viewModel = viewModel,
                            interestLabel = interestLabel,
                            loanLabel = loanLabel,
                            onClick = {
                                navigateToDetail(navController, loan) //navigate to detail
                            }
                        )
                    }
                }
            }
        }
    }
}

fun navigateToDetail(navController: NavController, loan: Loan) {
    // Loan json'ında id olsa navController.navigate("loanDetail/${loan.id}") bu şekilde yapılabilirdi.
    navController.currentBackStackEntry?.savedStateHandle?.set(NavigationBundleEnum.LOAN.key, loan)
    navController.navigate(NavigationEnum.DETAIL.screen)
}