package com.loanmanagementapp.ui.base

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun BaseText(
    text: String,
    color: Color? = null,
) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = color ?: Color.Unspecified,
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
