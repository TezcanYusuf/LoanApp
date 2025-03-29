package com.loanmanagementapp.ui.base

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun BaseText(
    text: String,
) {
    Text(
        text = text,
        fontSize = 16.sp
    )
}

@Composable
fun BaseTitleText(
    text: String,
) {
    Text(
        text = text,
        fontSize = 20.sp,
    )
}
