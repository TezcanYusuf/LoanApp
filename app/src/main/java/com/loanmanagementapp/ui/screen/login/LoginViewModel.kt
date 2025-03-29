package com.loanmanagementapp.ui.screen.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.loanmanagementapp.data.User
import com.loanmanagementapp.utils.PreferenceManagerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val preferenceManager: PreferenceManagerImpl
) : ViewModel() {

    private val username = mutableStateOf("")

    private val password = mutableStateOf("")

    fun onUsernameChange(newUsername: String) {
        username.value = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        password.value = newPassword
    }

    private fun saveUser() {
        preferenceManager.user =
            User(userName = username.value)
    }

    //else kısmında hata setlenmeli ve hata mesajı gösterilmeli
    fun checkUserNamePassword(onLoginSuccess: () -> Unit) {
        if (username.value == "admin" && password.value == "1234") {
            saveUser()
            onLoginSuccess.invoke()
        }
    }
}