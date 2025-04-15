package com.loanmanagementapp.data

import com.loanmanagementapp.utils.PreferenceManagerImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val preferenceManager: PreferenceManagerImpl
) {
    suspend fun execute(user: User, password: String): Flow<User> = flow {
        if (user.userName == ("admin") && password == "1234") {
            preferenceManager.user = user
            emit(user)
            return@flow
        } //todo t type ekle içinde user ve status olsun status ok ise üstte dön
        //  değilse bu todonun altında else yazma
    }
}