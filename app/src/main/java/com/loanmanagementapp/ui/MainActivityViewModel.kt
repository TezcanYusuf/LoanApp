package com.loanmanagementapp.ui

import androidx.lifecycle.ViewModel
import com.loanmanagementapp.utils.PreferenceManagerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    preferenceManager: PreferenceManagerImpl
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(preferenceManager.user != null)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun login() {
        _isLoggedIn.value = true
    }
}