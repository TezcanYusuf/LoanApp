package com.loanmanagementapp.ui.screen.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loanmanagementapp.data.LoginUseCase
import com.loanmanagementapp.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private var loginUseCase: LoginUseCase
) : ViewModel() {

    private val username = mutableStateOf("")

    private val password = mutableStateOf("")

    fun onUsernameChange(newUsername: String) {
        username.value = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
    }

    fun checkUserNamePassword(onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            loginUseCase.execute(User(userName = username.value), password = password.value)
                .collect {
                    onLoginSuccess()
                }
        }
    }
}