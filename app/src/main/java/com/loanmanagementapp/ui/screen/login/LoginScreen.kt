package com.loanmanagementapp.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loanmanagementapp.R
import com.loanmanagementapp.ui.base.BaseText
import com.loanmanagementapp.ui.base.FormField

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current

    val usernameLabel = stringResource(id = R.string.userName)
    val passwordLabel = stringResource(id = R.string.password)
    val loginButtonLabel = stringResource(id = R.string.login_button)
    val loginError = viewModel.loginErrorMessage.value

    LaunchedEffect(loginError) {
        loginError?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        val userNameValue by viewModel.username

        FormField(
            value = userNameValue,
            onValueChange = viewModel::onUsernameChange,
            label = usernameLabel,
            errorText = null,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        val passwordValue by viewModel.password

        FormField(
            value = passwordValue,
            onValueChange = viewModel::onPasswordChange,
            label = passwordLabel,
            isPassword = true,
            errorText = null,
            modifier = Modifier.fillMaxWidth()
        )//todo provider yaz password ve username için observable

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.checkUserNamePassword(onLoginSuccess)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            BaseText(loginButtonLabel)
        }
    }
}