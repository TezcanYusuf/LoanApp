package com.loanmanagementapp.ui.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loanmanagementapp.data.LoginUseCase
import com.loanmanagementapp.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.loanmanagementapp.data.Result


@HiltViewModel
class LoginViewModel @Inject constructor(
    private var loginUseCase: LoginUseCase
) : ViewModel() {

    private val _username = mutableStateOf("")
    val username: State<String> get() = _username

    private val _password = mutableStateOf("")
    val password: State<String> get() = _password

    private val _loginErrorMessage = mutableStateOf<String?>(null)
    val loginErrorMessage: State<String?> get() = _loginErrorMessage

    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun checkUserNamePassword(onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            loginUseCase.execute(User(userName = username.value), password = password.value)
                .collect {
                    when (it) {
                        is Result.Success -> {
                            onLoginSuccess()
                            _loginErrorMessage.value = null // önceki hata varsa temizle
                        }
                        is Result.Error -> {
                            _loginErrorMessage.value = it.message
                        }
                    }
                }
        }
    }

    fun clearError() {
        _loginErrorMessage.value = null
    }
}