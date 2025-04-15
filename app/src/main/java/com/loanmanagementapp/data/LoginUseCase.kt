package com.loanmanagementapp.data

import com.loanmanagementapp.ext.isValidEmail
import com.loanmanagementapp.ext.isValidPassword
import com.loanmanagementapp.utils.PreferenceManagerImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val preferenceManager: PreferenceManagerImpl
) {
    suspend fun execute(user: User, password: String): Flow<Result<User>> = flow {
        if (user.userName.isValidEmail() && password.isValidPassword() && user.userName == "admin@gmail.com" && password == "123456") {
            preferenceManager.user = user
            emit(Result.Success(user))
            return@flow
        }
        emit(Result.Error("Kullanıcı adı veya şifre yanlış"))
    }
}