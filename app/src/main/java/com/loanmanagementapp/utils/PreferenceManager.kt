package com.loanmanagementapp.utils

import com.loanmanagementapp.data.User

interface PreferenceManager {
    /**
     * Logged in User
     */
    var user: User?
}