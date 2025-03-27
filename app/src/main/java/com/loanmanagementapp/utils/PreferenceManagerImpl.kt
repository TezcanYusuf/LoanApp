package com.loanmanagementapp.utils

import android.content.SharedPreferences
import com.loanmanagementapp.data.User
import com.loanmanagementapp.ext.get
import com.loanmanagementapp.ext.set
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManagerImpl
@Inject constructor(private val sharedPreferences: SharedPreferences) : PreferenceManager {

    override var user: User?
        get() = sharedPreferences.get(USER)
        set(value) {
            sharedPreferences.set(USER, value)
        }

    companion object {
        private const val USER = "user"
    }
}