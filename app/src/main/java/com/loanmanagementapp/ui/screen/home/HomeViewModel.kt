package com.loanmanagementapp.ui.screen.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.data.LoanUpdateUseCase
import com.loanmanagementapp.utils.PreferenceManagerImpl
import com.loanmanagementapp.utils.strategy.calculate.LoanCalculatorProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferenceManager: PreferenceManagerImpl,
    private val loanCalculatorProvider: LoanCalculatorProvider,
    private val loanUpdateUseCase: LoanUpdateUseCase //todo use case yaz use case'de preferenca userı kaydet
) : ViewModel() {

    private val _loans = MutableStateFlow<List<Loan>>(emptyList())
    val loans: StateFlow<List<Loan>> = _loans

    fun getUserName(): String? {
        return preferenceManager.user?.userName
    }

    fun calculateLoanAmount(loan: Loan): Int {
        return loanCalculatorProvider.calculateLoan(loan)
    }

    suspend fun updateLoans(context: Context) {
        if (loans.value.isNotEmpty()) {
            _loans.value = emptyList()
            return
        }
        loanUpdateUseCase.execute(context).collect { updatedLoans ->
            _loans.value = updatedLoans // Krediler güncellenince UI'ı güncelle
        } //todo flow kullan döndürecek compose'a veriyo
    }

    fun logout() {
        preferenceManager.user = null
    }
}