package com.loanmanagementapp.ui.screen.login

import androidx.compose.runtime.State
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

    private val _username = mutableStateOf("")
    val username: State<String> = _username

    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
    }

    fun saveUser() {
        preferenceManager.user =
            User(userName = username.value)
    }
}